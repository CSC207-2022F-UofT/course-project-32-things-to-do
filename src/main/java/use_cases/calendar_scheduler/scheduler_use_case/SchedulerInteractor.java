package use_cases.calendar_scheduler.scheduler_use_case;

import entities.*;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictOutputBoundary;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictRequestModel;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictResponseModel;
import use_cases.task_management.todolist_use_case.TaskType;
import use_cases.task_management.todolist_use_case.ToDoListItem;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class SchedulerInteractor implements SchedulerInputBoundary {

    final ScheduleConflictOutputBoundary conflictPresenter;

    public SchedulerInteractor(ScheduleConflictOutputBoundary scheduleConflictOutputBoundary) {
        this.conflictPresenter = scheduleConflictOutputBoundary;
    }

    /**
     * Schedule the task in the given request model and return the corresponding response model
     * @param requestModel - the input from the user
     */
    @Override
    public SchedulerResponseModel schedule(SchedulerRequestModel requestModel) {

        SchedulerResponseModel responseModel = new SchedulerResponseModel(false);

        // Get the task to be scheduled
        Task task = requestModel.getTask();
        StudentUser user = requestModel.getUser();

        // Get the user's existing tasks
        ArrayList<String> taskIDs = user.getToDoList();
        ArrayList<Task> allTasks = new ArrayList<>();
        for (String taskID : taskIDs) {
            //get the Task object for this task from the entity TaskMap static variable
            Task existingTask = TaskMap.getTaskMap().get(taskID);
            allTasks.add(existingTask);
        }

        // Check if task has a time conflict with an existing task
        if (task instanceof Timeblockable) {
            LocalDateTime startTime = ((Timeblockable) task).getTimeBlock()[0];
            LocalDateTime endTime = ((Timeblockable) task).getTimeBlock()[1];
            Task conflictingTask = checkTimeConflict(startTime, endTime, allTasks);

            if (conflictingTask != null && !Objects.equals(task.getId(), conflictingTask.getId())) {
                // Alert user that time conflict exists
                ScheduleConflictRequestModel conflictRequestModel = new ScheduleConflictRequestModel(conflictingTask);
                ScheduleConflictResponseModel conflictResponseModel = conflictPresenter.alertConflict(conflictRequestModel);

                // Cancel scheduling if user chooses to
                if (!conflictResponseModel.isScheduleWithConflict()) {
                    responseModel.setScheduleCancel(true);
                    return responseModel;
                }
            }
        }

        // Schedule prep time if required
        if (task instanceof Preparatory) {
            schedulePrepTime(user, (Preparatory) task, allTasks);
        }

        return responseModel;
    }

    /**
     * Check if task has a time conflict with an existing task
     * If a time conflict exists, return the conflicting task
     * Otherwise, return null
     * @param startTime - the start time of the given task
     * @param endTime - the end time of the given task
     * @param allTasks - the user's to-do list
     */
    private Task checkTimeConflict(LocalDateTime startTime, LocalDateTime endTime, ArrayList<Task> allTasks) {

        // Check each existing task for time conflict
        for (Task existingTask : allTasks) {
            if (existingTask instanceof Timeblockable) {
                // Get start and end times of existingTask
                LocalDateTime existingStartTime = ((Timeblockable) existingTask).getTimeBlock()[0];
                LocalDateTime existingEndTime = ((Timeblockable) existingTask).getTimeBlock()[1];

                // Check if start or end time of task falls between start and end times of existingTask
                if ((startTime.isAfter(existingStartTime) && startTime.isBefore(existingEndTime)) ||
                        (endTime.isAfter(existingStartTime) && endTime.isBefore(existingEndTime))) {

                    return existingTask;
                }
            }
        }

        return null;
    }

    /**
     * Schedule the prep time for all preparatory tasks, including the newly created task
     * @param user - the given StudentUser
     * @param allTasks - the given StudentUser's to-do list
     */
    private void schedulePrepTime(StudentUser user, Preparatory task, ArrayList<Task> allTasks) {

        // Get all available times
        ArrayList<ArrayList<LocalDateTime>> availableTimes = getAvailableTimes(user, allTasks);
        ArrayList<Preparatory> preparatoryTasks = new ArrayList<>();

        // Get preparatory tasks sorted by time left
        allTasks.add((Task) task);
        for (Task existingTask : allTasks) {
            if (existingTask instanceof Preparatory) {
                preparatoryTasks.add((Preparatory) existingTask);
            }
        }
        preparatoryTasks.sort(Comparator.comparingDouble(Preparatory::getTimeLeft));

        // Schedule prep time for each preparatory task
        for (Preparatory preparatoryTask : preparatoryTasks) {
            // Get time remaining for the task
            double timeRemaining = preparatoryTask.getTimeNeeded() - preparatoryTask.getTimeSpent();

            // Schedule prep time
            int i = 0;
            ArrayList<ArrayList<LocalDateTime>> prepTimes = new ArrayList<>();

            while (timeRemaining > 0) {
                // Get next block of available time
                ArrayList<LocalDateTime> availableTime = availableTimes.get(i);
                long availableDuration = Duration.between(availableTime.get(0), availableTime.get(1)).toMinutes();

                // Subtract remaining time needed from block of available time
                if (timeRemaining < availableDuration) {
                    LocalDateTime updatedStartTime = availableTime.get(0).plusHours((long) timeRemaining);

                    ArrayList<LocalDateTime> updatedAvailableTime = new ArrayList<>();
                    updatedAvailableTime.add(updatedStartTime);
                    updatedAvailableTime.add(availableTime.get(1));

                    ArrayList<LocalDateTime> prepTime = new ArrayList<>();
                    prepTime.add(availableTime.get(0));
                    prepTime.add(updatedStartTime);

                    prepTimes.add(prepTime);
                    availableTimes.set(i, updatedAvailableTime);
                }

                // Remove block of available time from list
                else {
                    ArrayList<LocalDateTime> prepTime = new ArrayList<>();
                    prepTime.add(availableTime.get(0));
                    prepTime.add(availableTime.get(1));
                    prepTimes.add(prepTime);
                    availableTimes.remove(i);
                    i -= 1;
                }

                // Update iterators
                timeRemaining -= availableDuration;
                i += 1;
            }

            preparatoryTask.setPrepTimeScheduled(prepTimes);
        }

    }

    /**
     * Get the available working times for the given StudentUser
     * @param user - the given StudentUser
     * @param allTasks - the given StudentUser's to-do list
     */
    private ArrayList<ArrayList<LocalDateTime>> getAvailableTimes(StudentUser user, ArrayList<Task> allTasks) {

        // Get all existing time blocks
        ArrayList<LocalDateTime[]> existingTimeBlocks = new ArrayList<>();
        for (Task existingTask : allTasks) {
            if (existingTask instanceof Timeblockable) {
                existingTimeBlocks.add(((Timeblockable) existingTask).getTimeBlock());
            }
        }

        // Block out non-working hours
        ArrayList<LocalTime> workingHours = user.getWorkingHours();
        LocalTime workingStartTime;
        LocalTime workingEndTime;
        if (workingHours != null) {
            workingStartTime = workingHours.get(0);
            workingEndTime = workingHours.get(1);
        } else {
            workingStartTime = LocalTime.of(7, 0);
            workingEndTime = LocalTime.of(23, 0);
        }

        LocalDate currDate = LocalDate.now();
        LocalDate date = currDate;
        while (date.isBefore(currDate.plusMonths(1))) {
            LocalDateTime endTime = LocalDateTime.of(date.plusDays(1), workingStartTime);
            LocalDateTime startTime = LocalDateTime.of(date, workingEndTime);
            LocalDateTime[] timeBlock = {startTime, endTime};
            existingTimeBlocks.add(timeBlock);
            date = date.plusDays(1);
        }
        existingTimeBlocks.sort(Comparator.comparing(tb -> tb[0]));

        // Get all available times
        LocalDateTime currTime = LocalDateTime.now();
        ArrayList<ArrayList<LocalDateTime>> availableTimes = new ArrayList<>();

        for (LocalDateTime[] block : existingTimeBlocks) {
            if (currTime.isBefore(block[0])) {
                ArrayList<LocalDateTime> availableTime = new ArrayList<>();
                availableTime.add(currTime);
                availableTime.add(block[0]);
                availableTimes.add(availableTime);
            }

            if (currTime.isBefore(block[1])) {
                currTime = block[1];
            }
        }

        return availableTimes;

    }
}

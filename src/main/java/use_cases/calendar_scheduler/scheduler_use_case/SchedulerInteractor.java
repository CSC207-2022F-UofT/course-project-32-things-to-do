package use_cases.calendar_scheduler.scheduler_use_case;

import entities.Preparatory;
import entities.StudentUser;
import entities.Task;
import entities.Timeblockable;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictPresenter;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictRequestModel;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictResponseModel;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

public class SchedulerInteractor implements SchedulerInputBoundary {

    final ScheduleConflictPresenter conflictPresenter;
    final SchedulerPresenter schedulerPresenter;

    public SchedulerInteractor(ScheduleConflictPresenter scheduleConflictPresenter, SchedulerPresenter schedulerPresenter) {
        this.conflictPresenter = scheduleConflictPresenter;
        this.schedulerPresenter = schedulerPresenter;
    }

    /**
     * Schedule the task in the given request model and return the corresponding response model
     * @param requestModel - the input from the user
     */
    @Override
    public SchedulerResponseModel schedule(SchedulerRequestModel requestModel) {

        // Get the task to be scheduled
        Task task = requestModel.getTask();
        ArrayList<Task> allTasks = requestModel.getAllTasks();
        StudentUser user = requestModel.getUser();

        // Check if task has a time conflict with an existing task
        if (task instanceof Timeblockable) {
            LocalDateTime startTime = ((Timeblockable) task).getTimeBlock()[0];
            LocalDateTime endTime = ((Timeblockable) task).getTimeBlock()[1];
            Task conflictingTask = checkTimeConflict(startTime, endTime, allTasks);

            if (conflictingTask != null) {
                // Alert user that time conflict exists
                ScheduleConflictRequestModel conflictRequestModel = new ScheduleConflictRequestModel(conflictingTask);
                ScheduleConflictResponseModel conflictResponseModel = conflictPresenter.alertConflict(conflictRequestModel);

                // Schedule the conflicting task
                if (!conflictResponseModel.isScheduleConflict()) {
                    SchedulerResponseModel responseModel = new SchedulerResponseModel(task, allTasks);
                    return schedulerPresenter.prepareFailView(responseModel);
                }
            }
        }

        allTasks.add(task);

        // Schedule prep time if required
        schedulePrepTime(user, allTasks);

        // Create a response model from the updated to-do list
        SchedulerResponseModel responseModel = new SchedulerResponseModel(task, allTasks);

        return schedulerPresenter.prepareSuccessView(responseModel);
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
    private void schedulePrepTime(StudentUser user, ArrayList<Task> allTasks) {

        // Get all available times
        ArrayList<LocalDateTime[]> availableTimes = getAvailableTimes(user, allTasks);
        ArrayList<Preparatory> preparatoryTasks = new ArrayList<>();

        // Get preparatory tasks sorted by time left
        for (Task existingTask : allTasks) {
            if (existingTask instanceof Preparatory) {
                preparatoryTasks.add((Preparatory) existingTask);
            }
        }
        preparatoryTasks.sort(Comparator.comparingDouble(Preparatory::getTimeLeft));

        // Schedule prep time for each preparatory task
        for (Preparatory preparatoryTask : preparatoryTasks) {
            // Get time remaining for the task
            double timeRemaining = preparatoryTask.getTimeLeft();

            // Schedule prep time
            int i = 0;
            while (timeRemaining > 0) {
                LocalDateTime[] availableTime = availableTimes.get(i);
                long availableDuration = Duration.between(availableTime[0], availableTime[1]).toHours();
                ArrayList<LocalDateTime[]> prepTimes = new ArrayList<>();

                if (timeRemaining < availableDuration) {
                    LocalDateTime updatedStartTime = availableTime[0].plusHours((long) timeRemaining);
                    LocalDateTime[] updatedAvailableTime = {updatedStartTime, availableTime[1]};
                    LocalDateTime[] prepTime = {availableTime[0], updatedStartTime};
                    prepTimes.add(prepTime);
                    availableTimes.set(i, updatedAvailableTime);
                } else {
                    LocalDateTime[] prepTime = {availableTime[0], availableTime[1]};
                    prepTimes.add(prepTime);
                    availableTimes.remove(i);
                    i -= 1;
                }
                timeRemaining -= availableDuration;

                preparatoryTask.schedulePrepTime();
                i += 1;
            }
        }

    }

    /**
     * Get the available working times for the given StudentUser
     * @param user - the given StudentUser
     * @param allTasks - the given StudentUser's to-do list
     */
    private ArrayList<LocalDateTime[]> getAvailableTimes(StudentUser user, ArrayList<Task> allTasks) {

        // Get all existing time blocks
        ArrayList<LocalDateTime[]> existingTimeBlocks = new ArrayList<>();
        for (Task existingTask : allTasks) {
            if (existingTask instanceof Timeblockable) {
                existingTimeBlocks.add(((Timeblockable) existingTask).getTimeBlock());
            }
        }

        // Block out non-working hours
        ArrayList<LocalTime> workingHours = user.getWorkingHours();
        LocalTime workingStartTime = workingHours.get(0);
        LocalTime workingEndTime = workingHours.get(1);
        for (LocalDate date = LocalDate.now(); date.isBefore(date.plusYears(1)); date = date.plusDays(1)) {
            LocalDateTime endTime = LocalDateTime.of(date, workingStartTime);
            LocalDateTime startTime = LocalDateTime.of(date.plusDays(1), workingEndTime);
            LocalDateTime[] timeBlock = {endTime, startTime};
            existingTimeBlocks.add(timeBlock);
        }
        existingTimeBlocks.sort(Comparator.comparing(tb -> tb[0]));

        // Get all available times
        LocalDateTime currTime = LocalDateTime.now();
        ArrayList<LocalDateTime[]> availableTimes = new ArrayList<>();

        for (LocalDateTime[] block : existingTimeBlocks) {
            if (currTime.isBefore(block[0])) {
                LocalDateTime[] availableTime = {currTime, block[0]};
                availableTimes.add(availableTime);
            }

            if (currTime.isBefore(block[1])) {
                currTime = block[1];
            }
        }

        return availableTimes;

    }
}

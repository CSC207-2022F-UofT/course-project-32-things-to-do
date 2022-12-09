package use_cases.collaborative_task_scheduling.scheduling_ct_use_case;

import entities.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Scheduling Collaborative Tasks Use Case Interactor (use case layer)
 * Implements business logic on entities
 */

public class ScheduleCTInteractor implements ScheduleCTInputBoundary {
    private final ScheduleCTOutputBoundary scheduleCTOutputBoundary;
    final ScheduleCTDSGateway scheduleCTDSGateway;

    public ScheduleCTInteractor(ScheduleCTOutputBoundary scheduleCTOutputBoundary,
                                ScheduleCTDSGateway scheduleCTDSGateway) {
        this.scheduleCTOutputBoundary = scheduleCTOutputBoundary;
        this.scheduleCTDSGateway = scheduleCTDSGateway;
    }

    /**
     * The main controller of this interactor that calls the helper methods
     */
    @Override
    public ScheduleCTResponseModel schedule(ScheduleCTRequestModel requestModel) {

        HashMap<String, Task> taskHashMap =  TaskMap.getTaskMap();

        CollaborativeTask task = getTaskObjectFromName(requestModel.getTaskName(), taskHashMap);

        StudentUser currentUser = (StudentUser) CurrentUser.getCurrentUser();

        if (currentUser != task.getLeader()) {
            return scheduleCTOutputBoundary.prepareFailView("User is not the leader. " +
                    "You do not have scheduling access");
        }

        ArrayList<StudentUser> users = task.getTeammates();
        // if there are no teammates, there is nothing to schedule
        if (users != null) {
            users.add(task.getLeader());
        } else {
            return scheduleCTOutputBoundary.prepareFailView("No teammates found. Cannot schedule collaborative task.");
        }


        ArrayList<StudentUser> unavailableUsers = new ArrayList<>();

        LocalDateTime startTime = convertStringToLocalDateTime(requestModel.getStartTime());
        LocalDateTime endTime = convertStringToLocalDateTime(requestModel.getEndTime());

        for (StudentUser user : users) {
            ArrayList<Task> userTasks = getAllTaskFromIdExceptOne(task, user, taskHashMap);
            // isUserAvailableAtDateTime returns false if not available
            // if isUserAvailableAtDateTime is false, add it to the list of unavailable users
            if (!isUserAvailableAtDateTime(user, userTasks, startTime, endTime)) {
                unavailableUsers.add(user);
            }
        }
        // if unavailableUser is empty, that means that the users are free and there's no conflict
        if (unavailableUsers.isEmpty()) {
            // if task is recurring and the frequency is not an empty string
            ArrayList<ArrayList<LocalDateTime>> dates = getDates(task.getFrequency(), startTime, endTime, task.getDeadline());

            // initialize ArrayList<String> for all the formatted strings
            ArrayList<String> formattedDateTimes = new ArrayList<>();

            for (ArrayList<LocalDateTime> possibleTimeBlock : dates) {
                LocalDateTime possibleTimeStart = possibleTimeBlock.get(0);
                LocalDateTime possibleTimeEnd = possibleTimeBlock.get(1);
                String formattedTimeBlock = convertLocalDateTimeToString(possibleTimeStart, possibleTimeEnd);
                formattedDateTimes.add(formattedTimeBlock);
            }

            ScheduleCTResponseModel scheduleCTResponseModel = new ScheduleCTResponseModel(formattedDateTimes);
            scheduleCTResponseModel.setTimesToSchedule(dates);
            task.setTimeBlocks(dates);

            // updating the task map with the updated task
            scheduleCTDSGateway.updateTaskMap(task.getId(), task);

            return scheduleCTOutputBoundary.prepareNoConflictView(scheduleCTResponseModel);
            // branches into else when there is a conflict
        } else {
            // ScheduleCTResponseModel scheduleCTResponseModel = new ScheduleCTResponseModel(true);
            return scheduleCTOutputBoundary.prepareFailView("Cannot schedule due to conflict. " +
                    "Try another time if you'd like to. :')");
        }
    }

    /**
     * Finds all the dates to meet up given the frequency of a task
     * @param frequency - either "daily", "weekly" or "monthly"
     * @param startTime - the start time of the time block
     * @param endTime - the end time of the time block
     * @param deadline - the deadline of the task
     * @return an array list of array lists of local date time (i.e. the dates to meet up)
     */
    public ArrayList<ArrayList<LocalDateTime>> getDates(String frequency, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime deadline) {
        ArrayList<ArrayList<LocalDateTime>> times = new ArrayList<>();
        ArrayList<LocalDateTime> initialTime = new ArrayList<>();
        initialTime.add(startTime);
        initialTime.add(endTime);
        times.add(initialTime);

        LocalDateTime currStart = startTime;
        LocalDateTime currEnd = endTime;

        switch (frequency) {
            case "daily":
                while (currEnd.plusDays(1).isBefore(deadline)) {
                    ArrayList<LocalDateTime> date = new ArrayList<>();
                    currStart = currStart.plusDays(1);
                    currEnd = currEnd.plusDays(1);

                    date.add(currStart);
                    date.add(currEnd);
                    times.add(date);
                }
                break;
            case "weekly":
                while (currEnd.plusWeeks(1).isBefore(deadline)) {
                    ArrayList<LocalDateTime> date = new ArrayList<>();
                    currStart = currStart.plusWeeks(1);
                    currEnd = currEnd.plusWeeks(1);

                    date.add(currStart);
                    date.add(currEnd);
                    times.add(date);
                }
                break;
            case "monthly":
                while (currEnd.plusMonths(1).isBefore(deadline)) {
                    ArrayList<LocalDateTime> date = new ArrayList<>();
                    currStart = currStart.plusMonths(1);
                    currEnd = currEnd.plusMonths(1);

                    date.add(currStart);
                    date.add(currEnd);
                    times.add(date);
                }
                break;
        }
        return times;
    }

    /**
     * Check if a user is available at a fixed date time
     * @param user - the student user
     * @param tasks - an array list of a user's tasks
     * @param start - the start time of the time block
     * @param end -the end time of the time block
     * @return true if user is available, return false is user is not available
     */
    public boolean isUserAvailableAtDateTime(StudentUser user, ArrayList<Task> tasks, LocalDateTime start,
                                             LocalDateTime end) {
        // assume there's a method in TaskUseCase that gets all the tasks a student has

        ArrayList<Boolean> isAvailable = new ArrayList<>();

        boolean is_working_hours_free = true;
        for (Task task : tasks) {
            if (task instanceof Event) {
                if (((Event) task).getTimeBlock() != null) {
                    isAvailable.add(eventFree((Event) task, start, end));
                }
            } else if (task instanceof Test) {
                if (((Test) task).getTimeBlock() != null) {
                    isAvailable.add(testFree((Test) task, start, end));
                }
            } else if (task instanceof Assignment) {
                if (((Assignment) task).getPrepTimeScheduled() != null) {
                    isAvailable.add(assignmentFree((Assignment) task, start, end));
                }
            } else if (task instanceof CollaborativeTask) {
                if (((CollaborativeTask) task).getTimeBlocks() != null) {
                    isAvailable.add(collaborativeTaskFree((CollaborativeTask) task, start, end));
                }
            }

        }
        if (user.getWorkingHours() != null) {
            ArrayList<LocalTime> workingHours = user.getWorkingHours();
            LocalTime workingHoursStart = workingHours.get(0);
            LocalTime workingHoursEnd = workingHours.get(1);
            if (!workingHoursFree(start, end, workingHoursStart, workingHoursEnd)) {
                is_working_hours_free = false;
            }
        }
        // is_task_free is true if the array list contains false; that means there is a conflict
        boolean is_task_free = !(isAvailable.contains(false));
        return is_task_free && is_working_hours_free;
    }

    /**
     * Given a task that is an instance of Event, return whether any of the time blocks associated with that task
     * does not conflict with the given time block
     * @param event - the Event that you want to see if conflicts occur
     * @param start - the start date and time of the time block
     * @param end - the end date and time of the time block
     * @return - true if there are no conflicts, false if there are conflicts
     */
    public boolean eventFree(Event event, LocalDateTime start, LocalDateTime end) {

        LocalDateTime[] timeBlock = event.getTimeBlock();
        LocalDateTime timeBlockStart = timeBlock[0];
        LocalDateTime timeBlockEnd = timeBlock[1];
        return givenTime(timeBlockStart, timeBlockEnd, start, end);
    }

    /**
     * Given a task that is an instance of Test, return whether any of the time blocks associated with that task
     * does not conflict with the given time block
     * @param test - the Test that you want to see if conflicts occur
     * @param start - the start date and time of the time block
     * @param end - the end date and time of the time block
     * @return - true if there are no conflicts, false if there are conflicts
     */
    public boolean testFree(Test test, LocalDateTime start, LocalDateTime end) {
        LocalDateTime[] timeBlock = test.getTimeBlock();
        LocalDateTime timeBlockStart = timeBlock[0];
        LocalDateTime timeBlockEnd = timeBlock[1];

        boolean is_test_free = givenTime(timeBlockStart, timeBlockEnd, start, end);

        return prepTimeFree(start, end, is_test_free, test.getPrepTimeScheduled());
    }

    /**
     * Helper method to help determine if task is free
     * @param start - the start date and time of the time the user wants to schedule
     * @param end - the end date and time of the time the user wants to schedule
     * @param is_free - whether the task conflicts or not
     * @param prepTimeScheduled - the associated time blocks with a task
     * @return whether the time blocks conflict or not
     */
    private boolean prepTimeFree(LocalDateTime start, LocalDateTime end, boolean is_free, ArrayList<ArrayList<LocalDateTime>> prepTimeScheduled) {

        if (prepTimeScheduled != null){
            for (ArrayList<LocalDateTime> prep : prepTimeScheduled) {
                LocalDateTime prepStart = prep.get(0);
                LocalDateTime prepEnd = prep.get(1);

                // if there is a conflict
                if (!givenTime(prepStart, prepEnd, start, end)) {
                    is_free = false;
                }
            }
        }
        return is_free;
    }

    /**
     * Given a task that is an instance of Assignment, return whether any of the time blocks associated with that task
     * does not conflict with the given time block
     * @param assignment - the Assignment that you want to see if conflicts occur
     * @param start - the start date and time of the time block
     * @param end - the end date and time of the time block
     * @return true if there are no conflicts, false if there are conflicts
     */
    public boolean assignmentFree(Assignment assignment, LocalDateTime start, LocalDateTime end) {

        boolean is_assignment_free = true;
        return prepTimeFree(start, end, is_assignment_free, assignment.getPrepTimeScheduled());
    }

    /**
     * Given a task that is an instance of CollaborativeTask, return whether any of the time blocks associated with that
     * task does not conflict with the given time block
     * @param collaborativeTask - the CollaborativeTask that you want to see if conflicts occur
     * @param start - the start date and time of the time block
     * @param end - the end date and time of the time block
     * @return true if there are no conflicts, false if there are conflicts
     */
    public boolean collaborativeTaskFree(CollaborativeTask collaborativeTask, LocalDateTime start, LocalDateTime end) {
        boolean is_collaborative_task_free = true;

        return prepTimeFree(start, end, is_collaborative_task_free, collaborativeTask.getTimeBlocks());
    }

    /**
     * Check if a time block conflicts with a user's working hours
     * @param timeBlockStart - the start of the time block
     * @param timeBlockEnd - the end of the time block
     * @param workingHoursStart - the start of a user's working hours
     * @param workingHoursEnd - the end of a user's working hours
     * @return true if there are no conflict, false if there are conflicts
     */
    public boolean workingHoursFree(LocalDateTime timeBlockStart, LocalDateTime timeBlockEnd,
                                    LocalTime workingHoursStart, LocalTime workingHoursEnd) {
        // if timeBlock is within working hours
        if (timeBlockStart.getHour() > workingHoursStart.getHour() &&
                timeBlockEnd.getHour() < workingHoursEnd.getHour()) {
            return false;
            // if timeBlock covers whole working hours
        } else if (timeBlockStart.getHour() < workingHoursStart.getHour() &&
                timeBlockEnd.getHour() > workingHoursEnd.getHour()) {
            return false;
            // if timeBlockStart is before workingHoursStart, timeBlockEnd is after workingHoursStart,
            // and timeBlockEnd is before workingHoursEnd
        } else if (timeBlockStart.getHour() < workingHoursStart.getHour() &&
                timeBlockEnd.getHour() > workingHoursStart.getHour() &&
                timeBlockEnd.getHour() < workingHoursEnd.getHour()) {
            return false;
            // if timeBlockStart is after workingHoursStart, timeBlockStart is before workingHoursEnd,
            // and timeBlockEnd is after workingHoursEnd
        } else if (timeBlockStart.getHour() > workingHoursStart.getHour() &&
                timeBlockStart.getHour() < workingHoursEnd.getHour() &&
                timeBlockEnd.getHour() > workingHoursEnd.getHour()) {
            return false;
            // if timeBlockEnd is equal to workingHoursEnd
        } else if (timeBlockEnd.getHour() == workingHoursEnd.getHour()) {
            return false;
            // if timeBlockStart is equal to workingHoursStart
        } else return timeBlockStart.getHour() != workingHoursStart.getHour();
    }

    /**
     * Given two time blocks, return if they do not conflict
     * @param timeBlockStart - the start of the time block in a user's tasks
     * @param timeBlockEnd - the end of the time block in a user's tasks
     * @param start - the start of the time the user inputs
     * @param end - the end of the time the user inputs
     * @return true if there is no conflict, false is there is a conflict
     */
    public boolean givenTime(LocalDateTime timeBlockStart, LocalDateTime timeBlockEnd,
                             LocalDateTime start, LocalDateTime end) {
        // in the case where the time block is within the time of start and end
        if (timeBlockStart.isAfter(start) && timeBlockEnd.isBefore(end)) {
            return false;
            // in the case where it covers the time period
        } else if (timeBlockStart.isBefore(start) && timeBlockEnd.isAfter(end)) {
            return false;
            // in the case where timeBlockStart is before start, timeBlockEnd is after start,
            // and timeBlockEnd is before end
        } else if (timeBlockStart.isBefore(start) && timeBlockEnd.isAfter(start) && timeBlockEnd.isBefore(end)) {
            return false;
            // if the start time same as start time of block, return false
        } else if (timeBlockStart.isEqual(start)) {
            return false;
            // if the end time same as end time of block, return false
        } else if (timeBlockEnd.isEqual(end)) {
            return false;
            // in the case where timeBlockStart is after start time, timeBlockStart is before end time
            // and timeBlockEnd is after end time
        } else return !(timeBlockStart.isAfter(start) && timeBlockStart.isBefore(end) && timeBlockEnd.isAfter(end));
    }

    /**
     * Retrieve the tasks associated with a user from the given hash map, other than the task that you want scheduled
     * (remove that task or else when checking for conflicts, it might conflict with that task, don't want that)
     * @param user - the student user
     * @param hashMap - a hash map of all task ids to tasks
     * @return an array list of tasks
     */
    public ArrayList<Task> getAllTaskFromIdExceptOne(Task task, StudentUser user, HashMap<String, Task> hashMap) {
        ArrayList<Task> userTasks = new ArrayList<>();

        ArrayList<String> toDoList = user.getToDoList();

        for (String taskId : toDoList) {
            Task task_value = hashMap.get(taskId);
            userTasks.add(task_value);
        }
        userTasks.remove(task);
        return userTasks;
    }

    /**
     * Get the task object from the task map given the name of the task
     * @param taskName - the name of the task
     * @param hashMap - hashmap of task ids to tasks
     * @return the task object that corresponds to the task name, if it exists
     */
    public CollaborativeTask getTaskObjectFromName(String taskName, HashMap<String, Task> hashMap) {
        for (Task task : hashMap.values()) {
            if (task.getTitle().equals(taskName) && task instanceof CollaborativeTask) {
                return (CollaborativeTask) task;
            }
        }
        throw new RuntimeException("Task does not exist");
    }

    /**
     * Convert the given string to a LocalDateTime object
     * @param stringTime - the string representation of a date time
     * @return the string formatted as LocalDateTime
     */
    public LocalDateTime convertStringToLocalDateTime(String stringTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(stringTime, formatter);
    }

    /**
     * Convert the given LocalDateTime objects into a string
     * @param start - the start time and date as a LocalDateTime object
     * @param end - the end time and date as a LocalDateTime object
     * @return the LocalDateTime objects concatenated as a string with the word "to" between
     */
    public String convertLocalDateTimeToString(LocalDateTime start, LocalDateTime end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedStart = start.format(formatter);
        String formattedEnd = end.format(formatter);
        return formattedStart + " to " + formattedEnd;
    }
}
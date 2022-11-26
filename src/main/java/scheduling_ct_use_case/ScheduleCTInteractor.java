package scheduling_ct_use_case;

import entities.*;
import scheduler_use_case.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Scheduling Collaborative Tasks Use Case Interactor (use case layer)
 * Implements business logic on entities
 */

public class ScheduleCTInteractor implements ScheduleCTInputBoundary {
    private final ScheduleCTOutputBoundary scheduleCTOutputBoundary;

    public ScheduleCTInteractor(ScheduleCTOutputBoundary scheduleCTOutputBoundary) {
        this.scheduleCTOutputBoundary = scheduleCTOutputBoundary;
    }

    /**
     * The main controller of this interactor that calls the helper methods
     */
    @Override
    public ScheduleCTResponseModel schedule(ScheduleCTRequestModel requestModel, HashMap<String, Task> hashMap,
                                            SchedulerInteractor schedulerInteractor) {

        ArrayList<ArrayList<LocalDateTime>> dates = new ArrayList<>();

        CollaborativeTask task = getTaskObjectFromName(requestModel.getTaskName(), hashMap);

        if (requestModel.getStudentUser() != task.getLeader()) {
            return scheduleCTOutputBoundary.prepareFailView("User is not the leader. " +
                    "You do not have scheduling access");
        }

        ArrayList<StudentUser> users = task.getTeammates();
        users.add(task.getLeader());

        ArrayList<StudentUser> unavailableUsers = new ArrayList<>();

        LocalDateTime startTime = convertStringToLocalDateTime(requestModel.getStartTime());
        LocalDateTime endTime = convertStringToLocalDateTime(requestModel.getEndTime());

        for (StudentUser user : users) {
            ArrayList<Task> userTasks = getAllTaskFromIdExceptOne(task, user, hashMap);
            // isUserAvailableAtDateTime returns false if not available
            // if isUserAvailableAtDateTime is false, add it to the list of unavailable users
            if (!isUserAvailableAtDateTime(user, userTasks, startTime, endTime)) {
                unavailableUsers.add(user);
            }
        }
        // if unavailableUser is empty, that means that the users are free and there's no conflict
        if (unavailableUsers.isEmpty()) {
            // if task is recurring and the frequency is not an empty string
            if (task.getRecurring() && task.getFrequency() != "") {
                dates = getDates(task.getFrequency(), startTime, endTime, task.getDeadline());
                task.setTimeBlocks(dates);
            } else {
                ArrayList<LocalDateTime> theDate = new ArrayList<>();
                theDate.add(startTime);
                theDate.add(endTime);
                dates.add(theDate);
                task.setTimeBlocks(dates);
            }
            ScheduleCTResponseModel scheduleCTResponseModel = new ScheduleCTResponseModel(false);
            scheduleCTResponseModel.setTimesToSchedule(dates);
            task.setTimeBlocks(dates);
            for (StudentUser user : users) {
                addToCalendar(user, task, schedulerInteractor, hashMap);
            }
            return scheduleCTOutputBoundary.prepareNoConflictView(scheduleCTResponseModel);
        // branches into else when there is a conflict
        } else {
            // ScheduleCTResponseModel scheduleCTResponseModel = new ScheduleCTResponseModel(true);
            return scheduleCTOutputBoundary.prepareFailView("Cannot schedule due to conflict. " +
                    "Try another time if you'd like to. :')");
        }
    }

    /**
     * Adds the time block to a user's calendar
     * @param user - the user whose calendar is going to be updated
     * @param task - the task being added to the calendar
     * @param schedulerInteractor - scheduler interactor from scheduler_use_case
     * @param hashMap - hash map of task ids mapped to the task object
     * @return the scheduler response model
     */
    public SchedulerResponseModel addToCalendar(StudentUser user, CollaborativeTask task,
                                                SchedulerInteractor schedulerInteractor, HashMap<String, Task> hashMap) {
        // iterate through the array list of users
        // call getAllTaskFromIdExceptOne on the arrayList of studentUsers (gets all tasks except one wanting to
        // schedule, bc if you also get the one you want to schedule, schedule conflict might come up)
        // create a SchedulerRequestModel and call

        ArrayList<Task> allStudentTask = getAllTaskFromIdExceptOne(task, user, hashMap);
        SchedulerRequestModel schedulerRequestModel = new SchedulerRequestModel(task, allStudentTask, user);
        return schedulerInteractor.schedule(schedulerRequestModel);
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
                do {
                    ArrayList<LocalDateTime> date = new ArrayList<>();
                    currStart = currStart.plusDays(1);
                    currEnd = currEnd.plusDays(1);

                    date.add(currStart);
                    date.add(currEnd);
                    times.add(date);
                } while (endTime.isBefore(deadline));
                break;
            case "weekly":
                do {
                    ArrayList<LocalDateTime> date = new ArrayList<>();
                    currStart = currStart.plusWeeks(1);
                    currEnd = currEnd.plusWeeks(1);

                    date.add(currStart);
                    date.add(currEnd);
                    times.add(date);
                } while (endTime.isBefore(deadline));
                break;
            case "monthly":
                do {
                    ArrayList<LocalDateTime> date = new ArrayList<>();
                    currStart = currStart.plusMonths(1);
                    currEnd = currEnd.plusMonths(1);

                    date.add(currStart);
                    date.add(currEnd);
                    times.add(date);
                } while (endTime.isBefore(deadline));
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

        ArrayList<ArrayList<LocalDateTime>> prepTime = test.getPrepTimeScheduled();

        if (prepTime != null){
            for (ArrayList<LocalDateTime> prep : prepTime) {
                LocalDateTime prepStart = prep.get(0);
                LocalDateTime prepEnd = prep.get(1);

                // if there is a conflict
                if (!givenTime(prepStart, prepEnd, start, end)) {
                    is_test_free = false;
                }
            }
        }
        return is_test_free;
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
        ArrayList<ArrayList<LocalDateTime>> prepTime = assignment.getPrepTimeScheduled();

        if (prepTime != null) {
            for (ArrayList<LocalDateTime> prep : prepTime) {
                LocalDateTime prepStart = prep.get(0);
                LocalDateTime prepEnd = prep.get(1);
                if (!givenTime(prepStart, prepEnd, start, end)) {
                    is_assignment_free = false;
                }
            }
        }
        return is_assignment_free;
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

        ArrayList<ArrayList<LocalDateTime>> meetingTimes = collaborativeTask.getTimeBlocks();
         if (meetingTimes != null) {
             for (ArrayList<LocalDateTime> timeBlock : meetingTimes) {
                 LocalDateTime timeStart = timeBlock.get(0);
                 LocalDateTime timeEnd = timeBlock.get(1);
                 if (!givenTime(timeStart, timeEnd, start, end)) {
                     is_collaborative_task_free = false;
                 }
             }
         }
         return is_collaborative_task_free;
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
        } else if (timeBlockStart.getHour()< workingHoursStart.getHour() &&
                timeBlockEnd.getHour() > workingHoursStart.getHour() &&
                timeBlockEnd.getHour() < workingHoursEnd.getHour()) {
            return false;
        // if timeBlockStart is after workingHoursStart, timeBlockStart is before workingHoursEnd,
            // and timeBlockEnd is after workingHoursEnd
        } else if (timeBlockStart.getHour() > workingHoursStart.getHour() &&
                timeBlockStart.getHour() < workingHoursEnd.getHour() &&
                timeBlockEnd.getHour() > workingHoursEnd.getHour()) {
            return false;
        // if timeBlockStart is equal to workingHoursStart
        } else if (timeBlockStart.getHour() == workingHoursStart.getHour()) {
            return false;
            // if timeBlockStart is after workingHoursStart and timeBlockEnd is after workingHoursEnd
        } else {
            return true;
        }
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
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(stringTime, format);
    }

}

package use_case;
import entities.CollaborativeTask;
import entities.StudentUser;
import entities.Task;
import entities.Timeblockable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class CollaborativeScheduling{

    // methods I still might need:
    //      remove time block, call on CollaborativeTask method and just set time block to null
    //      remove scheduled time (i.e. remove from UI)
    //      add scheduled time (i.e. add to UI)


    /**
     * Check if the user is the host
     * @param task - the CollaborativeTask
     * @param user - the StudentUser
     * @return - if the user is the host (i.e. the leader of task, and the user match)
     */
    public boolean isUserHost(CollaborativeTask task, StudentUser user) {
        String user_name = user.getName();
        return user_name.equals(task.getLeader().getName());
    }

    /**
     * Find the times when all users are available
     * @param users - a list of StudentUsers associated with a CollaborativeTask
     * @param deadline - the deadline of the CollaborativeTask
     * @return - a list of times when all users are free from now to the deadline
     */
    public ArrayList<ArrayList<LocalDateTime>> allAvailableTimes(ArrayList<StudentUser> users, LocalDateTime deadline) {

        // Initialize LocalDateTime of the time right now
        LocalDateTime now = LocalDateTime.now();

        // Call nearestHour on the time - set that as start time
        LocalDateTime start = nearestHour(now);

        // Add an hour to start time - set that as end time
        LocalDateTime end = start.plusHours(1);

        // Initialize empty list
        ArrayList<ArrayList<LocalDateTime>> possible_times = new ArrayList<ArrayList<LocalDateTime>>();

        // Loop through time blocks at hour intervals, from the nearest hour to the deadline
        do {
            // Call areMembersAvailableAtFixedTime on each start and end time
            boolean isAllAvailable = areMembersAvailableAtFixedTime(users, start, end);
            // Creating an array list of the start and end time
            ArrayList<LocalDateTime> time = new ArrayList<>();
            time.add(start);
            time.add(end);
            // if isAllAvailable is true, add the array list to possible_times
            if (isAllAvailable) {
                possible_times.add(time);
            }
            // increment start by one hour
            start = start.plusHours(1);
            // increment end by one hour
            end = end.plusHours(1);
        } while (end.isBefore(deadline));

        // return array list of time blocks where every user is free
        return possible_times;
    }

    /**
     * Round a time to the nearest hour
     * @param time - a LocalDateTime object
     * @return - time rounded to the nearest hour
     */
    public LocalDateTime nearestHour(LocalDateTime time) {

        // If minutes equals 30 or more, add 1 hour
        int minutes = time.getMinute();
        // filler because I need to make next_time variable outside of if statement
        LocalDateTime next_time = LocalDateTime.now();
        if (minutes >= 30) {
            next_time = time.plusHours(1);
        }

        return next_time.truncatedTo(ChronoUnit.HOURS);

    }

    /**
     * Check if all users are available at a fixed time block
     * @param users - a list of StudentUsers associated with a CollaborativeTask
     * @param start - the start time of a time block
     * @param end - the end time of a time block
     * @return - if all StudentUsers in users are available at the specified time block
     */
    public boolean areMembersAvailableAtFixedTime(ArrayList<StudentUser> users,
                                                  LocalDateTime start, LocalDateTime end) {

        for (StudentUser user : users) {
            boolean isAvailable;
            Task[] allTasks = getAllTasks(user); // need to implement this
            isAvailable = isUserAvailableAtDateTime(user,allTasks, start, end);
            if (!isAvailable) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if a user is available at a time block
     * @param user - a StudentUser
     * @param tasks - a list of Task objects associated with the StudentUser, user
     * @param start - the start of a time block
     * @param end - the end time of a time block
     * @return - if the user is available at the specified time block
     */
    public boolean isUserAvailableAtDateTime(StudentUser user, Task[] tasks, LocalDateTime start, LocalDateTime end) {
        // assume there's a method in TaskUseCase that gets all the tasks a student has

        boolean is_task_free = true;
        boolean is_working_hours_free = true;
        for (Task task : tasks) {
            // issue with this because assignments are given time blocks as well, but aren't timeblockable
            // however, there's no time block attribute in assignments
            if (task instanceof Timeblockable) {
                LocalDateTime[] timeBlock = task.getTimeBlock;
                LocalDateTime timeBlockStart = timeBlock[0];
                LocalDateTime timeBlockEnd = timeBlock[1];

                is_task_free = givenTime(timeBlockStart, timeBlockEnd, start, end);
            }
        }
        List<List<LocalDateTime>> workingHours = user.getWorkingHours();
        for (List<LocalDateTime> block : workingHours) {
            LocalDateTime startTimeBlock = block.get(0);
            LocalDateTime endTimeBlock = block.get(1);
            // if the start time of the working hours is within the time of start and end, return false
            is_working_hours_free =  givenTime(startTimeBlock, endTimeBlock, start, end);

        }
        return is_task_free & is_working_hours_free;

    }

    /**
     * Check if given two time blocks, they conflict
     * @param timeBlockStart - the start time of the time block
     * @param timeBlockEnd - the end time of the time block
     * @param start - the start of a time block to be compared to
     * @param end - the end time of a time block to be compared to
     * @return - if the time blocks conflict
     */
    public boolean givenTime(LocalDateTime timeBlockStart, LocalDateTime timeBlockEnd,
                             LocalDateTime start, LocalDateTime end) {
        if (timeBlockStart.isAfter(start) & timeBlockStart.isBefore(end)) {
            return false;
            // if the end time of their time block is within the time of start and end, return false
        } else if (timeBlockEnd.isAfter(start) & timeBlockEnd.isBefore(end)) {
            return false;
            // if the start and end of time block is within start and end, return false
        } else if (timeBlockStart.isAfter(start) & timeBlockEnd.isBefore(end)) {
            return false;
            // if the start time same as start time of block, return false
        } else if (timeBlockStart.isEqual(start)) {
            return false;
            // if the end time same as end time of block, return false
        } else if (timeBlockEnd.isEqual(end)) {
            return false;
            // if the task start time and end time, covers the time period, return false
        } else return !(timeBlockStart.isBefore(start) & timeBlockEnd.isAfter(end));
    }

    // NEED TO IMPLEMENT: Given a user, get all their tasks
    /**
     * Get all the Task objects associated with a user (since a StudentUser object only stores the ids of a task)
     * @param user - a StudentUser
     * @return - a list of all their time blocks
     */
    public Task[] getAllTasks(StudentUser user) {
        return Task[];
    }



}

package use_case_collaborative_scheduling;
import entities.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class CollaborativeScheduling implements CollaborativeSchedulingInputBoundary{


    private final CollaborativeSchedulingOutputBoundary collaborativeSchedulingOutputBoundary;

    public CollaborativeScheduling(CollaborativeSchedulingOutputBoundary collaborativeSchedulingOutputBoundary){
        this.collaborativeSchedulingOutputBoundary = collaborativeSchedulingOutputBoundary;
    }

    /**
     * Main controller of this interactor
     */
    @Override
    public CollaborativeSchedulingResponseModel schedule(CollaborativeSchedulingRequestModel requestModel, TaskMap taskMap) {

        // request model only has the string name of the task
        // need to implement getTaskFromTaskTitle
        CollaborativeTask task = getTaskFromTitle(requestModel.getTaskTitle(), taskMap);
        if (task.getTitle().equals("No task")) {
            return collaborativeSchedulingOutputBoundary.prepareFailView("Task does not exist");
        }
        ArrayList<StudentUser> all_teammates = task.getTeammates();
        // adding the leader (user) to the array list
        all_teammates.add(task.getLeader());
        LocalDateTime task_deadline = task.getDeadline();

        ArrayList<ArrayList<LocalDateTime>> available_times = allAvailableTimes(all_teammates, task_deadline,
                taskMap);

        // if the list is empty does it return false because its nested? Nope - tested it works fine
        if (available_times.isEmpty()) {
            return collaborativeSchedulingOutputBoundary.prepareFailView("There are no available times.");
            // prepare view that says that there are no available times
        }

        switch (task.getFrequency()) {
            case "daily": {
                ArrayList<ArrayList<LocalDateTime>> filtered_times = filterDaily(available_times);
                CollaborativeSchedulingResponseModel responseModel = new CollaborativeSchedulingResponseModel(filtered_times);
                return collaborativeSchedulingOutputBoundary.prepareAvailableTimes(responseModel);
            }
            case "weekly": {
                ArrayList<ArrayList<LocalDateTime>> filtered_times = filterWeekly(available_times);
                CollaborativeSchedulingResponseModel responseModel = new CollaborativeSchedulingResponseModel(filtered_times);
                return collaborativeSchedulingOutputBoundary.prepareAvailableTimes(responseModel);
            }
            case "monthly": {
                ArrayList<ArrayList<LocalDateTime>> filtered_times = filterMonthly(available_times);
                CollaborativeSchedulingResponseModel responseModel = new CollaborativeSchedulingResponseModel(filtered_times);
                return collaborativeSchedulingOutputBoundary.prepareAvailableTimes(responseModel);
            }
        }
        CollaborativeSchedulingResponseModel responseNoFilter = new CollaborativeSchedulingResponseModel(available_times);
        return collaborativeSchedulingOutputBoundary.prepareAvailableTimes(responseNoFilter);

    }

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
     * Filter available times by the frequency daily
     * @param allTimes - all the available times for scheduling
     * @return - return an array list, filtered by the day
     */
    public ArrayList<ArrayList<LocalDateTime>> filterDaily(ArrayList<ArrayList<LocalDateTime>> allTimes) {
        // allTimes is an ArrayList of all available times
        // when the frequency is daily, from the time block in the array list, if other time blocks have the same
        // date, remove it from the array list

        for (int i = 0; i < allTimes.size(); i++){
            LocalDateTime startTimeBlock = allTimes.get(i).get(0);
            // LocalDateTime endTimeBlock = allTimes.get(i).get(1);

            LocalDateTime nextStartBlock = allTimes.get(i+1).get(0);
            // LocalDateTime nextEndBlock = allTimes.get(i+1).get(1);

            if (startTimeBlock.getDayOfYear() == nextStartBlock.getDayOfYear()) {
                allTimes.remove(i+1);
            }
        }
        return allTimes;

    }

    /**
     * Filter available times by frequency weekly
     * @param allTimes - all the available times for scheduling
     * @return - return an array list, filtered by the week
     */
    public ArrayList<ArrayList<LocalDateTime>> filterWeekly(ArrayList<ArrayList<LocalDateTime>> allTimes) {
        // allTimes is an ArrayList of all available times
        // when the frequency is weekly, from the time block in the array list,

        for (int i = 0; i < allTimes.size(); i++){
            LocalDateTime startTimeBlock = allTimes.get(i).get(0);
            LocalDateTime nextStartBlock = allTimes.get(i+1).get(0);

            int difference = nextStartBlock.getDayOfYear() - startTimeBlock.getDayOfYear();

            if (difference < 6){
                allTimes.remove(i+1);
            }
        }
        return allTimes;
    }

    /**
     * Filter available times by frequency monthly
     * @param allTimes - all the available times for scheduling
     * @return - return an array list, filtered by the month
     */
    public ArrayList<ArrayList<LocalDateTime>> filterMonthly(ArrayList<ArrayList<LocalDateTime>> allTimes) {
        // allTimes is an ArrayList of all available times
        // when the frequency is monthly, if a time block has the same month, remove it

        for (int i = 0; i < allTimes.size(); i++) {
            LocalDateTime startTimeBlock = allTimes.get(i).get(0);
            LocalDateTime nextStartBlock = allTimes.get(i + 1).get(0);

            if (startTimeBlock.getMonth() == nextStartBlock.getMonth()) {
                allTimes.remove(i + 1);
            }
        }
        return allTimes;
    }

    /**
     * Find the times when all users are available
     * @param users - a list of StudentUsers associated with a CollaborativeTask
     * @param deadline - the deadline of the CollaborativeTask
     * @return - a list of times when all users are free from now to the deadline
     */
    public ArrayList<ArrayList<LocalDateTime>> allAvailableTimes(ArrayList<StudentUser> users, LocalDateTime deadline,
                                                                 TaskMap allTasksEver) {

        // Initialize LocalDateTime of the time right now
        LocalDateTime now = LocalDateTime.now();

        // Call nearestHour on the time - set that as start time
        LocalDateTime start = nearestHour(now);

        // Add an hour to start time - set that as end time
        LocalDateTime end = start.plusHours(1);

        // Initialize empty list
        ArrayList<ArrayList<LocalDateTime>> possible_times = new ArrayList<>();

        // Loop through time blocks at hour intervals, from the nearest hour to the deadline
        do {
            // Call areMembersAvailableAtFixedTime on each start and end time
            boolean isAllAvailable = areMembersAvailableAtFixedTime(users, start, end, allTasksEver);
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
                                                  LocalDateTime start, LocalDateTime end,
                                                  TaskMap allTasksEver) {

        for (StudentUser user : users) {
            boolean isAvailable;
            ArrayList<Task> allTasks = getMyTasks(user, allTasksEver); // need to implement this
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
    public boolean isUserAvailableAtDateTime(StudentUser user, ArrayList<Task> tasks, LocalDateTime start,
                                             LocalDateTime end) {
        // assume there's a method in TaskUseCase that gets all the tasks a student has

        boolean is_task_free = true;
        boolean is_working_hours_free = true;
        for (Task task : tasks) {

            if (task instanceof Event) {
                LocalDateTime[] timeBlock = ((Event) task).getTimeBlock();
                LocalDateTime timeBlockStart = timeBlock[0];
                LocalDateTime timeBlockEnd = timeBlock[1];

                is_task_free = is_task_free && givenTime(timeBlockStart, timeBlockEnd, start, end);
            } else if (task instanceof Test) {
                LocalDateTime[] timeBlock = ((Test) task).getTimeBlock();
                LocalDateTime timeBlockStart = timeBlock[0];
                LocalDateTime timeBlockEnd = timeBlock[1];

                is_task_free = is_task_free && givenTime(timeBlockStart, timeBlockEnd, start, end);

                ArrayList<ArrayList<LocalDateTime>> scheduledPrep = ((Test) task).getPrepTimeScheduled();
                for (ArrayList<LocalDateTime> prep : scheduledPrep) {
                    LocalDateTime prepStart = prep.get(0);
                    LocalDateTime prepEnd = prep.get(1);

                    is_task_free = is_task_free && givenTime(prepStart, prepEnd, start, end);
                }
            } else if (task instanceof Assignment) {

                ArrayList<ArrayList<LocalDateTime>> scheduledPrep = ((Assignment) task).getPrepTimeScheduled();
                for (ArrayList<LocalDateTime> prep : scheduledPrep) {
                    LocalDateTime prepStart = prep.get(0);
                    LocalDateTime prepEnd = prep.get(1);

                    is_task_free = is_task_free && givenTime(prepStart, prepEnd, start, end);
                }
            }
        }
        ArrayList<LocalTime> workingHours = user.getWorkingHours();
        LocalTime workingHoursStart = workingHours.get(0);
        LocalTime workingHoursEnd = workingHours.get(1);
        is_working_hours_free = givenLocalDateTimeAndLocalTime(start,end, workingHoursStart, workingHoursEnd);

        return is_task_free & is_working_hours_free;

    }

    /**
     * Check if given times and dates do not conflict
     * @param timeBlockStart - start time of the time block
     * @param timeBlockEnd - end time of the time block
     * @param workingHoursStart - start of working hours
     * @param workingHoursEnd - end of working hours
     * @return - return whether the times and dates given do not conflict
     */
    public boolean givenLocalDateTimeAndLocalTime(LocalDateTime timeBlockStart, LocalDateTime timeBlockEnd,
                                                  LocalTime workingHoursStart, LocalTime workingHoursEnd) {
        // if timeBlock is within working hours
        if (timeBlockStart.isAfter(ChronoLocalDateTime.from(workingHoursStart)) &&
                timeBlockEnd.isBefore(ChronoLocalDateTime.from(workingHoursEnd))) {
            return false;
        // if timeBlock covers the whole period of working hours
        } else if (timeBlockStart.isBefore(ChronoLocalDateTime.from(workingHoursStart)) &&
                timeBlockEnd.isAfter(ChronoLocalDateTime.from(workingHoursEnd))) {
            return false;
        // if timeBlockStart is before workingHoursStart and timeBlockEnd is before workingHoursEnd
        } else if (timeBlockStart.isBefore(ChronoLocalDateTime.from(workingHoursStart)) &&
                timeBlockEnd.isBefore(ChronoLocalDateTime.from(workingHoursEnd))) {
            return false;
        // if timeBlockStart is the same as workingHoursStart
        } else if (timeBlockStart.isEqual(ChronoLocalDateTime.from(workingHoursStart))) {
            return false;
        // if timeBlockEnd is the same as workingHoursEnd
        } else if (timeBlockEnd.isEqual(ChronoLocalDateTime.from(workingHoursEnd))) {
            return false;
            // if timeBlockStart is after workingHoursStart and timeBlockEnd is after workingHoursEnd
        } else return !(timeBlockStart.isAfter(ChronoLocalDateTime.from(workingHoursStart)) &&
                timeBlockEnd.isAfter(ChronoLocalDateTime.from(workingHoursEnd)));
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
        // in the case where the time block is within the time of start and end
        if (timeBlockStart.isAfter(start) && timeBlockEnd.isBefore(end)) {
            return false;
            // in the case where it covers the time period
        } else if (timeBlockStart.isBefore(start) && timeBlockEnd.isAfter(end)) {
            return false;
            // in the case where timeBlockStart is before start and timeBlockEnd is before end
        } else if (timeBlockStart.isBefore(start) && timeBlockEnd.isBefore(end)) {
            return false;
            // if the start time same as start time of block, return false
        } else if (timeBlockStart.isEqual(start)) {
            return false;
            // if the end time same as end time of block, return false
        } else if (timeBlockEnd.isEqual(end)) {
            return false;
            // in the case where timeBlockStart is after start time and timeBlockEnd is after end time
        } else return !(timeBlockStart.isAfter(start) && timeBlockEnd.isAfter(end));
    }

    /**
     * Get all the Task objects associated with a user (since a StudentUser object only stores the ids of a task)
     * @param user - a StudentUser
     * @return - a list of all their time blocks
     */
    // go through user to do list (task ids as strings) and get all their tasks
    public ArrayList<Task> getMyTasks(StudentUser user, TaskMap allTasksEver) {
        // tasks ids are formatted as datetime-student-course
        // allTasksEver is every task in the program file

        // initialize empty array list
        ArrayList<Task> userTasks = new ArrayList<>();

        // getting student username
        String username = user.getName();

        // getting student task_ids
        ArrayList<String> task_ids = user.getToDoList();

        // iterating through keys
//        for (String key : allTasksEver.getTaskMap().keySet()) {
//            // if the username is in the key
//            if (key.contains(username)){
//                // get the task associated with the key
//                Task task_value = allTasksEver.getTaskMap().get(key);
//                // add it to the array list
//                userTasks.add(task_value);
//            }
//        }

        for (String taskObject : task_ids) {
            Task task_value = allTasksEver.getTaskMap().get(taskObject);
            userTasks.add(task_value);
        }

        return userTasks;
    }

    /**
     * Retrieve the task object if the task name matches the title given and is a CollaborativeTask
     * @param title - the title being looked for in the task map
     * @param taskMap - the TaskMap object (includes every single task title mapped to the task object)
     * @return the task if it is found, otherwise return the defaultTask
     */
    public CollaborativeTask getTaskFromTitle(String title, TaskMap taskMap){
        CollaborativeTask defaultTask = new CollaborativeTask("No task", 1, false, "daily");
        for (Task task : taskMap.getTaskMap().values()) {
            if (task.getTitle().equals(title) && task instanceof CollaborativeTask) {
                return (CollaborativeTask) task;
            }
        }
        return defaultTask;
    }



}

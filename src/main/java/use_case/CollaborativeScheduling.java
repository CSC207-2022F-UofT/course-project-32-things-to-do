package use_case;
import entities.*;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class CollaborativeScheduling implements CollaborativeSchedulingInputBoundary{


    private final CollaborativeSchedulingOutputBoundary outputBoundary;
    // methods I still might need:
    //      remove time block, call on CollaborativeTask method and just set time block to null
    //      remove scheduled time (i.e. remove from UI)
    //      add scheduled time (i.e. add to UI)

    public CollaborativeScheduling(CollaborativeSchedulingOutputBoundary outputBoundary){
        this.outputBoundary = outputBoundary;
    }


    @Override
    public CollaborativeSchedulingResponseModel schedule(CollaborativeSchedulingRequestModel requestModel) {

//        if (!isUserHost(requestModel.getTask(), requestModel.getUser())) {
//            // prepare view that says, user is not the host
//            return outputBoundary.prepareFailView("User is not the host.");
//        }

        ArrayList<StudentUser> all_teammates = requestModel.getTask().getTeammates();
        // adding the leader (user) to the array list
        all_teammates.add(requestModel.getTask().getLeader());
        LocalDateTime task_deadline = requestModel.getTask().getDeadline();
        TaskCreationUseCaseFiller allTasksEver = requestModel.getAllTasksEver();

        ArrayList<ArrayList<LocalDateTime>> available_times = allAvailableTimes(all_teammates, task_deadline,
                allTasksEver);

        // if the list is empty does it return false because its nested? Nope - tested it works fine
        if (available_times.isEmpty()) {
            return outputBoundary.prepareFailView("There are no available times.");
            // prepare view that says that there are no available times
        }

        switch (requestModel.getTask().getFrequency()) {
            case "daily": {
                ArrayList<ArrayList<LocalDateTime>> filtered_times = filterDaily(available_times);
                CollaborativeSchedulingResponseModel responseModel = new CollaborativeSchedulingResponseModel(filtered_times);
                return outputBoundary.prepareAvailableTimes(responseModel);
            }
            case "weekly": {
                ArrayList<ArrayList<LocalDateTime>> filtered_times = filterWeekly(available_times);
                CollaborativeSchedulingResponseModel responseModel = new CollaborativeSchedulingResponseModel(filtered_times);
                return outputBoundary.prepareAvailableTimes(responseModel);
            }
            case "monthly": {
                ArrayList<ArrayList<LocalDateTime>> filtered_times = filterMonthly(available_times);
                CollaborativeSchedulingResponseModel responseModel = new CollaborativeSchedulingResponseModel(filtered_times);
                return outputBoundary.prepareAvailableTimes(responseModel);
            }
        }


        CollaborativeSchedulingResponseModel responseNoFilter = new CollaborativeSchedulingResponseModel(available_times);
        return outputBoundary.prepareAvailableTimes(responseNoFilter);

        // return prepare(responseModel);

        // when interactor is done executing the use case, it needs to report back any information
        // that needs to be displayed in the View
        // the interactor creates the output data object then calls the output boundary method (implemented
        // by the presenter), passing the output data object as a parameter



        // is user is not the host, prepare view that says, user is not the host
        // if there are no available times to schedule, prepare view that says no available times

        // otherwise display the times the user can choose from
        // do response model stuff
    }

    // @Override
    // public CollaborativeSchedulingResponseModel addToCalendar(CollaborativeSchedulingRequestModel requestModel) {
        // ArrayList<ArrayList<LocalDateTime>> available_times = requestModel.getTimesChosenToSchedule();
        // iterate through all the times, and change the format, so it's easier to read?
        // need to consult Celine since our scheduled events are going to show up on the same UI
        // am I allowed to have the type as void? or should I just prepare a success view?? because I don't need to
        // present the times or anything, I just need to make sure they're in the calendar
    // }
    // don't need because can just call on Celine's controller to input into calendar UI
    // information gets passed down from view to controller -> call on celine's controller


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

    // filtering by the frequency and is either "daily", "weekly", "monthly"
    // otherwise frequency is blank
    // only do this if the frequency isn't blank
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
                                                                 TaskCreationUseCaseFiller allTasksEver) {

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
                                                  TaskCreationUseCaseFiller allTasksEver) {

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
        List<List<LocalDateTime>> workingHours = user.getWorkingHours();
        for (List<LocalDateTime> block : workingHours) {
            LocalDateTime startTimeBlock = block.get(0);
            LocalDateTime endTimeBlock = block.get(1);
            // if the start time of the working hours is within the time of start and end, return false
            is_working_hours_free =  is_working_hours_free && givenTime(startTimeBlock, endTimeBlock, start, end);

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
    // go through user to do list (task ids as strings) and get all their tasks
    public ArrayList<Task> getMyTasks(StudentUser user, TaskCreationUseCaseFiller allTasksEver) {
        // tasks ids are formatted as datetime-student-course
        // allTasksEver is every task in the program file

        // initialize empty array list
        ArrayList<Task> userTasks = new ArrayList<>();

        // getting student username
        String username = user.getName();

        // getting student task_ids
        ArrayList<String> task_ids = user.getToDoList();

        // iterating through keys
        for (String key : allTasksEver.getAllTasks().keySet()) {
            // if the username is in the key
            if (key.contains(username)){
                // get the task associated with the key
                Task task_value = allTasksEver.getAllTasks().get(key);
                // add it to the array list
                userTasks.add(task_value);
            }
        }

        return userTasks;
    }



}

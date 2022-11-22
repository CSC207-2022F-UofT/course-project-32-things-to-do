package scheduling_ct_use_case;

import entities.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleCTInteractor implements ScheduleCTInputBoundary {
    private final ScheduleCTOutputBoundary scheduleCTOutputBoundary;

    public ScheduleCTInteractor(ScheduleCTOutputBoundary scheduleCTOutputBoundary) {
        this.scheduleCTOutputBoundary = scheduleCTOutputBoundary;
    }

    @Override
    public ScheduleCTResponseModel schedule(ScheduleCTRequestModel requestModel, HashMap<String, Task> hashMap) {
        // returns that this has conflict
        // otherwise will automatically schedule and return a success view

        CollaborativeTask task = getTaskObjectFromName(requestModel.getTaskName(), hashMap);
        ArrayList<StudentUser> users = task.getTeammates();
        users.add(task.getLeader());

        ArrayList<StudentUser> unavailableUsers = new ArrayList<>();

        LocalDateTime startTime = convertStringToLocalDateTime(requestModel.getStartTime());
        LocalDateTime endTime = convertStringToLocalDateTime(requestModel.getEndTime());

        for (StudentUser user : users) {
            ArrayList<Task> userTasks = getTaskFromId(user, hashMap);
            if (!isUserAvailableAtDateTime(user, userTasks, startTime, endTime)) {
                unavailableUsers.add(user);
            }
        }
        if (!unavailableUsers.isEmpty()) {
            if (task.getFrequency().equals("")) {
                ArrayList<ArrayList<LocalDateTime>> oneDate = new ArrayList<>();
                ArrayList<LocalDateTime> theDate = new ArrayList<>();
                theDate.add(startTime);
                theDate.add(endTime);
                oneDate.add(theDate);
                task.setTimeBlocks(oneDate);
            } else {
                ArrayList<ArrayList<LocalDateTime>> dates = getDates(task.getFrequency(), startTime, endTime, task.getDeadline());
                task.setTimeBlocks(dates);
            }
            ScheduleCTResponseModel scheduleCTResponseModel = new ScheduleCTResponseModel(false);
            return scheduleCTOutputBoundary.prepareNoConflictView(scheduleCTResponseModel);
        } else {
            ScheduleCTResponseModel scheduleCTResponseModel = new ScheduleCTResponseModel(true);
            return scheduleCTOutputBoundary.prepareFailView("Cannot schedule due to conflict");
        }
    }

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


    public boolean isUserAvailableAtDateTime(StudentUser user, ArrayList<Task> tasks, LocalDateTime start,
                                             LocalDateTime end) {
        // assume there's a method in TaskUseCase that gets all the tasks a student has

        boolean is_task_free = true;
        boolean is_working_hours_free = true;
        for (Task task : tasks) {

            if (task instanceof Event) {
                if (((Event) task).getTimeBlock() != null) {
                    LocalDateTime[] timeBlock = ((Event) task).getTimeBlock();
                    LocalDateTime timeBlockStart = timeBlock[0];
                    LocalDateTime timeBlockEnd = timeBlock[1];
                    is_task_free = is_task_free && givenTime(timeBlockStart, timeBlockEnd, start, end);
                }
            } else if (task instanceof Test) {
                if (((Test) task).getTimeBlock() != null) {

                    LocalDateTime[] timeBlock = ((Test) task).getTimeBlock();
                    LocalDateTime timeBlockStart = timeBlock[0];
                    LocalDateTime timeBlockEnd = timeBlock[1];

                    is_task_free = is_task_free && givenTime(timeBlockStart, timeBlockEnd, start, end);

                    if (((Test) task).getPrepTimeScheduled() != null) {
                        ArrayList<ArrayList<LocalDateTime>> scheduledPrep = ((Test) task).getPrepTimeScheduled();
                        for (ArrayList<LocalDateTime> prep : scheduledPrep) {
                            LocalDateTime prepStart = prep.get(0);
                            LocalDateTime prepEnd = prep.get(1);

                            is_task_free = is_task_free && givenTime(prepStart, prepEnd, start, end);
                        }

                    }
                }
            } else if (task instanceof Assignment) {

                if (((Assignment) task).getPrepTimeScheduled() != null) {
                    ArrayList<ArrayList<LocalDateTime>> scheduledPrep = ((Assignment) task).getPrepTimeScheduled();
                    for (ArrayList<LocalDateTime> prep : scheduledPrep) {
                        LocalDateTime prepStart = prep.get(0);
                        LocalDateTime prepEnd = prep.get(1);

                        is_task_free = is_task_free && givenTime(prepStart, prepEnd, start, end);
                    }
                }
            }

        }
        if (user.getWorkingHours() != null) {
            ArrayList<LocalTime> workingHours = user.getWorkingHours();
            LocalTime workingHoursStart = workingHours.get(0);
            LocalTime workingHoursEnd = workingHours.get(1);
            is_working_hours_free = workingHoursFree(start, end, workingHoursStart, workingHoursEnd);
        }
        return is_task_free && is_working_hours_free;
    }

    // required methods:
    // - for each user determine if they have conflict with time given from their tasks
    // - for each user determine if they have conflict with time given with their working hours

    public boolean workingHoursFree(LocalDateTime timeBlockStart, LocalDateTime timeBlockEnd,
                                    LocalTime workingHoursStart, LocalTime workingHoursEnd) {
        // if timeBlock is within working hours
        // timeBlockStart.isAfter(ChronoLocalDateTime.from(workingHoursStart)) &&
        //                timeBlockEnd.isBefore(ChronoLocalDateTime.from(workingHoursEnd))
        if (timeBlockStart.getHour() > workingHoursStart.getHour() &&
                timeBlockEnd.getHour() < workingHoursEnd.getHour()) {
            return false;
            // if timeBlock covers the whole period of working hours
            // timeBlockStart.isBefore(ChronoLocalDateTime.from(workingHoursStart)) &&
            //                timeBlockEnd.isAfter(ChronoLocalDateTime.from(workingHoursEnd))
        } else if (timeBlockStart.getHour() < workingHoursStart.getHour() &&
                timeBlockEnd.getHour() > workingHoursEnd.getHour()) {
            return false;
            // if timeBlockStart is before workingHoursStart and timeBlockEnd is before workingHoursEnd
        } else if (timeBlockStart.getHour()< workingHoursStart.getHour() &&
                timeBlockEnd.getHour() < workingHoursEnd.getHour()) {
            return false;
            // if timeBlockStart is the same as workingHoursStart
        } else if (timeBlockStart.getHour() == workingHoursStart.getHour()) {
            return false;
            // if timeBlockEnd is the same as workingHoursEnd
        } else if (timeBlockEnd.getHour() == workingHoursEnd.getHour()) {
            return false;
            // if timeBlockStart is after workingHoursStart and timeBlockEnd is after workingHoursEnd
        } else return !(timeBlockStart.getHour() > workingHoursStart.getHour() &&
                timeBlockEnd.getHour() > workingHoursEnd.getHour());
    }
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


    public ArrayList<Task> getTaskFromId(StudentUser user, HashMap<String, Task> hashMap) {
        ArrayList<Task> userTasks = new ArrayList<>();

        ArrayList<String> toDoList = user.getToDoList();

        for (String taskId : toDoList) {
            Task task_value = hashMap.get(taskId);
            userTasks.add(task_value);
        }
        return userTasks;
    }

    /**
     * Get the task object from the task map given the name of the task
     * @param taskName - the name of the task
     * @param hashMap - hashmap of task ids to tasks
     * @return the task object that corresponds to the task name
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

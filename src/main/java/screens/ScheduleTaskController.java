package screens;

import entities.CollaborativeTask;
import entities.StudentUser;
import entities.TaskMap;
import entities.Task;
import use_case_schedule_ct.ScheduleTaskInputBoundary;
import use_case_schedule_ct.ScheduleTaskRequestModel;
import use_case_schedule_ct.ScheduleTaskResponseModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScheduleTaskController {

    private final ScheduleTaskInputBoundary input;

    private final TaskMap databaseTasks;

    public ScheduleTaskController(ScheduleTaskInputBoundary input, TaskMap databaseTasks) {

        this.input = input;
        this.databaseTasks = databaseTasks;
    }
    // i think you can just pass the task over to the SchedulerInteractor as a SchedulerRequestModel
    // (which takes the given task, the user's existing list of tasks, and the user themselves)

    // the best way to follow clean architecture in this case would be to create an instance of the SchedulerController
    // in Main then inject it into your controller

    public ScheduleTaskResponseModel addTimes(String taskString, String timesString){
        ArrayList<ArrayList<LocalDateTime>> times = stringDateTime(timesString);

        // pass task over to SchedulerInteractor as SchedulerRequestModel
        // takes given task, user's existing list of tasks, and user themselves
        CollaborativeTask task = getTaskFromTitle(taskString);

        ArrayList<StudentUser> users = task.getTeammates();

//        for (StudentUser user : users) {
//            // what do I want to do:
//            // make each one into a request model and pass it onto SchedulerInteractor
//            ArrayList<Task> userTasks = getTaskListFromId(user.getToDoList());
//            SchedulerRequestModel schedulerRequestModel = new SchedulerRequestModel(task, userTasks, user);
//            SchedulerInputBoundary schedulerInputBoundary = new SchedulerInputBoundary();
//            SchedulerController schedulerController = new SchedulerController(SchedulerInputBoundary taskInput);
//
//
//        }
        ScheduleTaskRequestModel inputData = new ScheduleTaskRequestModel(task, times);
        return input.schedule(inputData);
    }
    public ArrayList<Task> getTaskListFromId(ArrayList<String> taskIds) {
        ArrayList<Task> taskObjects = new ArrayList<>();
        for (String id : taskIds) {
            Task task = databaseTasks.getTaskMap().get(id);
            taskObjects.add(task);
        }
        return taskObjects;
    }

    public CollaborativeTask getTaskFromTitle(String title){
        // iterating through map values
        for (Task task : databaseTasks.getTaskMap().values()){
            // if the task title is equal to the title given, and it is a collaborative task, return the task
            if (task.getTitle().equals(title) && task instanceof CollaborativeTask) {
                return (CollaborativeTask) task;
            }
        }
        throw new SchedulingTimesFailed("Task does not exist. Make sure the title is correct");
    }


    public ArrayList<ArrayList<LocalDateTime>> stringDateTime(String timesChosen){
        // first separate the strings by commas
        String[] splitStrings = timesChosen.split(",");

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        ArrayList<ArrayList<LocalDateTime>> scheduleTimes = new ArrayList<>();

        // for each string in splitStrings, split them by the word "to"
        for (String timeBlock : splitStrings) {
            String[] splitByTime = timeBlock.split(" to ");
            String start = splitByTime[0];
            String end = splitByTime[1];
            LocalDateTime startTime = LocalDateTime.parse(start, format);
            LocalDateTime endTime = LocalDateTime.parse(end, format);

            ArrayList<LocalDateTime> allTimes = new ArrayList<>();
            allTimes.add(startTime);
            allTimes.add(endTime);

            scheduleTimes.add(allTimes);
        }
        return scheduleTimes;
    }

}

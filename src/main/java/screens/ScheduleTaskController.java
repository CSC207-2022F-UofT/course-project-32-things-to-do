package screens;

import entities.CollaborativeTask;
import entities.TaskMap;
import entities.Task;
import use_case.*;

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
    // DON'T FORGET YOU NEED TO CALL ON CELINE'S CALENDAR INPUT METHOD FROM HER CONTROLLER

    public ScheduleTaskResponseModel addTimes(String taskString, String timesString){
        ArrayList<ArrayList<LocalDateTime>> times = stringDateTime(timesString);
        Task task = getTask(taskString);
        ScheduleTaskRequestModel inputData = new ScheduleTaskRequestModel((CollaborativeTask) task, times);
        return input.schedule(inputData);
    }
    public Task getTask(String title){
        // iterating through map values
        for (Task task : databaseTasks.getTaskMap().values()){
            // if the task title is equal to the title given, and it is a collaborative task, return the task
            if (task.getTitle().equals(title) && task instanceof CollaborativeTask) {
                return task;
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


    // call on Celine's controller here to input into calendar UI
}

package scheduler_use_case;

import entities.Task;

import java.util.ArrayList;

public class SchedulerResponseModel {

    private ArrayList<Task> allTasks;
    private Task task;

    /**
     * Create a response model for the scheduling use case
     * @param task - the given task
     * @param allTasks - the user's to-do list
     */
    public SchedulerResponseModel(Task task, ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
        this.task = task;
    }

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public void setAllTasks(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}

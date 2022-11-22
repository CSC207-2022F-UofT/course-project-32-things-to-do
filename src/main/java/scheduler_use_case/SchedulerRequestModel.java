package scheduler_use_case;

import entities.StudentUser;
import entities.Task;

import java.util.ArrayList;

public class SchedulerRequestModel {

    private Task task;
    private ArrayList<Task> allTasks;
    private StudentUser user;

    /**
     * Creates a request model with the given input
     * @param task - the given Task
     * @param allTasks - the given user's to-do list
     * @param user - the given StudentUser
     */
    public SchedulerRequestModel(Task task, ArrayList<Task> allTasks, StudentUser user) {
        this.task = task;
        this.allTasks = allTasks;
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public void setAllTasks(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public StudentUser getUser() {
        return user;
    }

    public void setUser(StudentUser user) {
        this.user = user;
    }
}

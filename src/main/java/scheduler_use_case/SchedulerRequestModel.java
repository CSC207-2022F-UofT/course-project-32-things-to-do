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
     * @param task - the newly created task
     * @param user - the student currently logged in
     */
    public SchedulerRequestModel(Task task, StudentUser user) {

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

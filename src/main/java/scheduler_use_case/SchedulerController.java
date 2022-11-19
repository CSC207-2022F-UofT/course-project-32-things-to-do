package scheduler_use_case;

import entities.StudentUser;
import entities.Task;

import java.util.ArrayList;

public class SchedulerController {

    /**
     * Objects required for dependency inversion
     */
    final SchedulerInputBoundary taskInput;

    /**
     * Initialize the controller with the given input
     * @param taskInput - the input from the user
     */
    public SchedulerController(SchedulerInputBoundary taskInput) {
        this.taskInput = taskInput;
    }

    /**
     * Create a request model with the given task and return the corresponding response model
     * @param task - the given task
     */
    SchedulerResponseModel schedule(Task task, ArrayList<Task> allTasks, StudentUser user) {
        SchedulerRequestModel requestModel = new SchedulerRequestModel(task, allTasks, user);

        return taskInput.schedule(requestModel);
    }
}

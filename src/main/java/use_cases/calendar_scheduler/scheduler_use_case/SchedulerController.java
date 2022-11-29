package use_cases.calendar_scheduler.scheduler_use_case;

import entities.Event;
import entities.StudentUser;
import use_cases.task_management.event_creation_use_case.EventCreationResponseModel;

import java.time.LocalDateTime;

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
     * @param responseModel - the created task's response model
     */
    public SchedulerResponseModel schedule(EventCreationResponseModel responseModel) {
        Event task = new Event("test", "test", 0, LocalDateTime.now(), LocalDateTime.now(), false, null);
        StudentUser user = new StudentUser("test", "test");
        SchedulerRequestModel requestModel = new SchedulerRequestModel(task, user);

        return taskInput.schedule(requestModel);
    }
}

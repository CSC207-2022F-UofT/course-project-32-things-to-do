package screens.task_management.task_creation_screens.test_creation_screens;

import use_cases.task_management.task_creation_use_case.TaskCreationInputBoundary;
import use_cases.task_management.task_creation_use_case.TaskCreationRequestModel;
import use_cases.task_management.task_creation_use_case.TaskCreationResponseModel;
import use_cases.task_management.task_creation_use_case.TestCreationRequestModel;

import java.time.LocalDateTime;

public class TestCreationController {
    final TaskCreationInputBoundary input;

    /**
     * A controller for interacting with the Task creation use case (specifically Test creation)
     * @param input - the input boundary for creating Tests
     */
    public TestCreationController(TaskCreationInputBoundary input) {
        this.input = input;
    }

    /**
     * Attempt to create a Test
     * @param title - the title of the Test
     * @param priority - the priority of the Test
     * @param startTime - the Test's start time
     * @param endTime - the Test's end time
     * @param weightage - the Test's weightage
     * @return - response model (input boundary will indicate success/failure)
     */
    TaskCreationResponseModel create(String title, int priority, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        // create request model
        TaskCreationRequestModel requestModel = new TestCreationRequestModel(title, priority, startTime, endTime, weightage);
        // try to create the Test
        return input.create(requestModel, "Test");
    }
}

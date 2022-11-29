package screens.task_management.test_creation_screens;

import use_cases.task_management.task_creation_use_case.TaskCreationInputBoundary;
import use_cases.task_management.task_creation_use_case.TaskCreationRequestModel;
import use_cases.task_management.task_creation_use_case.TaskCreationResponseModel;
import use_cases.task_management.task_creation_use_case.TestCreationRequestModel;

import java.time.LocalDateTime;

public class TestCreationController {
    final TaskCreationInputBoundary input;
    public TestCreationController(TaskCreationInputBoundary input) {
        this.input = input;
    }
    TaskCreationResponseModel create(String title, int priority, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        TaskCreationRequestModel requestModel = new TestCreationRequestModel(title, priority, startTime, endTime, weightage);
        return input.create(requestModel, "Test");
    }
}

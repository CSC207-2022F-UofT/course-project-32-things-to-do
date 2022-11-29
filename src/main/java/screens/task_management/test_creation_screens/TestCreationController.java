package test_creation_screens;

import task_creation_use_case.TaskCreationInputBoundary;
import task_creation_use_case.TaskCreationRequestModel;
import task_creation_use_case.TaskCreationResponseModel;
import task_creation_use_case.TestCreationRequestModel;

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

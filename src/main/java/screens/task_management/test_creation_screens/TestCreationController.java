package screens.task_management.test_creation_screens;

import use_cases.task_management.test_creation_use_case.TestCreationInputBoundary;
import use_cases.task_management.test_creation_use_case.TestCreationRequestModel;
import use_cases.task_management.test_creation_use_case.TestCreationResponseModel;

import java.time.LocalDateTime;

public class TestCreationController {
    final TestCreationInputBoundary input;
    public TestCreationController(TestCreationInputBoundary input) {
        this.input = input;
    }
    TestCreationResponseModel create(String title, int priority, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        TestCreationRequestModel requestModel = new TestCreationRequestModel(title, priority, startTime, endTime, weightage);
        return input.create(requestModel);
    }
}

package test_edit_screens;

import entities.Test;
import test_edit_use_case.TestEditInputBoundary;
import test_edit_use_case.TestEditRequestModel;
import test_edit_use_case.TestEditResponseModel;

import java.time.LocalDateTime;

public class TestEditController {
    final TestEditInputBoundary input;
    public TestEditController(TestEditInputBoundary input) {
        this.input = input;
    }

    public TestEditResponseModel edit(Test test, String title, int priority, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        TestEditRequestModel requestModel = new TestEditRequestModel(test, title, priority, startTime, endTime, weightage);
        return input.edit(requestModel);
    }

}

package test_edit_screens;

import entities.Test;
import task_edit_use_case.TaskEditInputBoundary;
import task_edit_use_case.TaskEditRequestModel;
import task_edit_use_case.TaskEditResponseModel;
import task_edit_use_case.TestEditRequestModel;

import java.time.LocalDateTime;

public class TestEditController {
    final TaskEditInputBoundary input;
    public TestEditController(TaskEditInputBoundary input) {
        this.input = input;
    }

    public TaskEditResponseModel edit(Test test, String title, int priority, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        TaskEditRequestModel requestModel = new TestEditRequestModel(test, title, priority, startTime, endTime, weightage);
        return input.edit(requestModel, "Test");
    }

}

package screens.task_management.task_edit_delete_screens.test_edit_delete_screens;

import use_cases.task_management.task_edit_use_case.TaskEditInputBoundary;
import use_cases.task_management.task_edit_use_case.TaskEditRequestModel;
import use_cases.task_management.task_edit_use_case.TaskEditResponseModel;
import use_cases.task_management.task_edit_use_case.TestEditRequestModel;

import java.time.LocalDateTime;

public class TestEditController {
    final TaskEditInputBoundary input;
    public TestEditController(TaskEditInputBoundary input) {
        this.input = input;
    }

    public TaskEditResponseModel edit(boolean complete, String id, String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                      double weightage, double timeNeeded, double timeSpent) {
        TaskEditRequestModel requestModel = new TestEditRequestModel(id, complete, title, priority, startTime, endTime, weightage,
                timeNeeded, timeSpent);
        return input.edit(requestModel, "Test");
    }

}

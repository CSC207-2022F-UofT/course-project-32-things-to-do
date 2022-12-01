package screens.task_management.task_edit_delete_screens.assignment_edit_delete_screens;

import entities.Assignment;
import use_cases.task_management.task_edit_use_case.AssignmentEditRequestModel;
import use_cases.task_management.task_edit_use_case.TaskEditInputBoundary;
import use_cases.task_management.task_edit_use_case.TaskEditRequestModel;
import use_cases.task_management.task_edit_use_case.TaskEditResponseModel;

import java.time.LocalDateTime;

public class AssignmentEditController {
    final TaskEditInputBoundary input;
    public AssignmentEditController(TaskEditInputBoundary input) {
        this.input = input;
    }

    public TaskEditResponseModel edit(String id, String title, int priority, LocalDateTime dueDate, double weightage,
                                      double timeNeeded, double timeSpent) {
        TaskEditRequestModel requestModel = new AssignmentEditRequestModel(
                id, title, priority, dueDate, weightage, timeNeeded, timeSpent);
        return input.edit(requestModel, "Assignment");
    }

}

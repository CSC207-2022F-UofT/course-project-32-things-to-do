package assignment_edit_screens;

import entities.Assignment;
import task_edit_use_case.AssignmentEditRequestModel;
import task_edit_use_case.TaskEditInputBoundary;
import task_edit_use_case.TaskEditRequestModel;
import task_edit_use_case.TaskEditResponseModel;

import java.time.LocalDateTime;

public class AssignmentEditController {
    final TaskEditInputBoundary input;
    public AssignmentEditController(TaskEditInputBoundary input) {
        this.input = input;
    }

    public TaskEditResponseModel edit(Assignment assignment, String title, int priority, LocalDateTime dueDate, double weightage) {
        TaskEditRequestModel requestModel = new AssignmentEditRequestModel(assignment, title, priority, dueDate, weightage);
        return input.edit(requestModel, "Assignment");
    }

}

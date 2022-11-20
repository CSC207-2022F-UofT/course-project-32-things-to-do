package screens;

import entities.Assignment;
import assignment_edit_use_case.AssignmentEditInputBoundary;
import assignment_edit_use_case.AssignmentEditRequestModel;
import assignment_edit_use_case.AssignmentEditResponseModel;

import java.time.LocalDateTime;

public class AssignmentEditController {
    final AssignmentEditInputBoundary input;
    public AssignmentEditController(AssignmentEditInputBoundary input) {
        this.input = input;
    }

    public AssignmentEditResponseModel edit(Assignment assignment, String title, int priority, LocalDateTime dueDate, double weightage) {
        AssignmentEditRequestModel requestModel = new AssignmentEditRequestModel(assignment, title, priority, dueDate, weightage);
        return input.edit(requestModel);
    }

}

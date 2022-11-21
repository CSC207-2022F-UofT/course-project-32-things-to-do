package assignment_creation_screens;

import assignment_creation_use_case.AssignmentCreationInputBoundary;
import assignment_creation_use_case.AssignmentCreationRequestModel;
import assignment_creation_use_case.AssignmentCreationResponseModel;

import java.time.LocalDateTime;

public class AssignmentCreationController {
    final AssignmentCreationInputBoundary input;
    public AssignmentCreationController(AssignmentCreationInputBoundary input) {
        this.input = input;
    }
    AssignmentCreationResponseModel create(String title, int priority, LocalDateTime dueDate, double weightage) {
        AssignmentCreationRequestModel requestModel = new AssignmentCreationRequestModel(title, priority, dueDate,
                weightage);
        return input.create(requestModel);
    }
}

package use_cases.task_management.assignment_creation_use_case;

public interface AssignmentCreationInputBoundary {
    AssignmentCreationResponseModel create(AssignmentCreationRequestModel requestModel);
}

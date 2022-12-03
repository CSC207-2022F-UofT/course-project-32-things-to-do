package use_cases.task_management.task_creation_use_case;

public interface TaskCreationInputBoundary {
    TaskCreationResponseModel create(TaskCreationRequestModel requestModel, String type);
}

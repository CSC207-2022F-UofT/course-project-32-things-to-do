package use_cases.collaborative_task_management.collaborative_task_creation_use_case;

public interface CollaborativeTaskCreationInputBoundary {
    /**
     * Attempt to create a Collaborative Task
     * @param requestModel - request model for creation
     * @return - response model
     */
    CollaborativeTaskCreationResponseModel create(CollaborativeTaskCreationRequestModel requestModel);
}
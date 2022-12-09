package use_cases.collaborative_task_management.collaborative_task_deletion_use_case;

public interface CollaborativeTaskDeletionInputBoundary {
    /**
     * Attempt to delete a Collaborative Task.
     * @param requestModel - request model for deletion.
     * @return - response model.
     */
    CollaborativeTaskDeletionResponseModel delete(CollaborativeTaskDeletionRequestModel requestModel);
}

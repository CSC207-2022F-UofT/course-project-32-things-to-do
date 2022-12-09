package use_cases.collaborative_task_management.collaborative_task_deletion_use_case;

public interface CollaborativeTaskDeletionPresenter {
    /**
     * Prepare a success view for when deletion is successful
     * @param collaborativeTaskDeletionResponseModel - response model for Collaborative Task deletion
     * @return - response model
     */
    CollaborativeTaskDeletionResponseModel prepareSuccessView(CollaborativeTaskDeletionResponseModel collaborativeTaskDeletionResponseModel);

    /**
     * Prepare a fail view for when deletion fails
     * @param error - error message
     * @return - throw a new CollaborativeTaskDeletionFailed error
     */
    CollaborativeTaskDeletionResponseModel prepareFailView(String error);
}

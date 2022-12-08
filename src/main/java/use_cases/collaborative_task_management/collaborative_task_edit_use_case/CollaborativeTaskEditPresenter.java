package use_cases.collaborative_task_management.collaborative_task_edit_use_case;

public interface CollaborativeTaskEditPresenter {
    /**
     * Prepare a success view for when editing is successful
     * @param collaborativeTaskEditResponseModel - response model for Collaborative Task editing
     * @return - response model
     */
    CollaborativeTaskEditResponseModel prepareSuccessView(CollaborativeTaskEditResponseModel collaborativeTaskEditResponseModel);

    /**
     * Prepare a fail view for when editing fails
     * @param error - error message
     * @return - throw a new CollaborativeTaskEditFailed error
     */
    CollaborativeTaskEditResponseModel prepareFailView(String error);
}
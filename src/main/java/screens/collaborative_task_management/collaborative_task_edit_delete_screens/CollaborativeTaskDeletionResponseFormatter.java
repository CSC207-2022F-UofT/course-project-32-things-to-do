package screens.collaborative_task_management.collaborative_task_edit_delete_screens;

import use_cases.collaborative_task_management.collaborative_task_deletion_use_case.*;

public class CollaborativeTaskDeletionResponseFormatter implements CollaborativeTaskDeletionPresenter{
    /**
     * Prepare a success view for when Collaborative Task deletion is successful
     * @param responseModel - response model for Collaborative Task deletion
     * @return - response model
     */
    @Override
    public CollaborativeTaskDeletionResponseModel prepareSuccessView(CollaborativeTaskDeletionResponseModel responseModel) { return responseModel; }

    /**
     * Prepare a fail view for when Collaborative Task deletion fails
     * @param error - error message
     * @return - throw a new CollaborativeTaskDeletionFailed error
     */
    @Override
    public CollaborativeTaskDeletionResponseModel prepareFailView(String error) { throw new CollaborativeTaskDeletionFailed(error); }
}

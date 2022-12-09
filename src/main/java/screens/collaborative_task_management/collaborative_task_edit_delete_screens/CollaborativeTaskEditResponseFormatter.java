package screens.collaborative_task_management.collaborative_task_edit_delete_screens;

import use_cases.collaborative_task_management.collaborative_task_edit_use_case.CollaborativeTaskEditPresenter;
import use_cases.collaborative_task_management.collaborative_task_edit_use_case.CollaborativeTaskEditResponseModel;

public class CollaborativeTaskEditResponseFormatter implements CollaborativeTaskEditPresenter{
    /**
     * Prepare a success view for when Collaborative Task editing is successful
     * @param responseModel - response model for Collaborative Task editing
     * @return - response model
     */
    @Override
    public CollaborativeTaskEditResponseModel prepareSuccessView(CollaborativeTaskEditResponseModel responseModel) { return  responseModel; };

    /**
     * Prepare a fail view for when Collaborative Taskediting fails
     * @param error - error message
     * @return - throw a new CollaborativeTaskEditFailed error
     */
    @Override
    public CollaborativeTaskEditResponseModel prepareFailView(String error) { throw new CollaborativeTaskEditFailed(error); };
}

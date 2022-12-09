package screens.collaborative_task_management.collaborative_task_creation_screens;

import use_cases.collaborative_task_management.collaborative_task_creation_use_case.CollaborativeTaskCreationOutputBoundary;
import use_cases.collaborative_task_management.collaborative_task_creation_use_case.CollaborativeTaskCreationResponseModel;

public class CollaborativeTaskCreationResponseFormatter implements CollaborativeTaskCreationOutputBoundary{
    /**
     * Prepare a success view for the successful Collaborative Task creation.
     * @param responseModel - a CollaborativeTaskCreationResponseModel for Collaborative Task creation
     * @return - the response model
     */
    @Override
    public CollaborativeTaskCreationResponseModel prepareSuccessView(CollaborativeTaskCreationResponseModel responseModel) { return responseModel; }

    /**
     * Prepare a fail view if Collaborative Task creation was unsuccessful
     * @param error - the type of error that happened
     */
    @Override
    public CollaborativeTaskCreationResponseModel prepareFailView(String error) { throw new CollaborativeTaskCreationFailed(error); }
}

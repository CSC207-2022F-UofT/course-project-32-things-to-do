package use_cases.collaborative_task_management.collaborative_task_creation_use_case;

/**
 * Output Boundary for the Collaborative Tasks Creation Use Case
 * (inverts dependency for interactor to presenter)
 */

public interface CollaborativeTaskCreationOutputBoundary {
    /**
     * The method implemented in the presenter that prepares the success view when there are no errors.
     *
     * @param responseModel - a CollaborativeTaskCreationResponseModel
     * @return a CollaborativeTaskCreationResponseModel
     */
    CollaborativeTaskCreationResponseModel prepareSuccessView(CollaborativeTaskCreationResponseModel responseModel);

    /**
     * The method implemented in the presenter that prepares the fail view for when there are errors.
     *
     * @param error - the type of error that happened
     * @return a CollaborativeTaskCreationResponseModel
     */
    CollaborativeTaskCreationResponseModel prepareFailView(String error);
}

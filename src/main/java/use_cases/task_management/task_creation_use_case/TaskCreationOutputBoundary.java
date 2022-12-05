package use_cases.task_management.task_creation_use_case;

public interface TaskCreationOutputBoundary {
    /**
     * Prepare a success view for the successful Task creation
     * @param response - response model for Task creation
     * @return - the response model
     */
    TaskCreationResponseModel prepareSuccessView(TaskCreationResponseModel response);

    /**
     * Prepare a fail view if Task creation was unsuccessful
     * @param error - the error
     */
    TaskCreationResponseModel prepareFailView(String error);
}

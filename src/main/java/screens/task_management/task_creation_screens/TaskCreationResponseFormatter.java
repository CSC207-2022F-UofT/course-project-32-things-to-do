package screens.task_management.task_creation_screens;

import use_cases.task_management.task_creation_use_case.*;

public class TaskCreationResponseFormatter implements TaskCreationOutputBoundary {
    /**
     * Prepare a success view for the successful Task creation
     * @param response - response model for Task creation
     * @return - the response model
     */
    @Override
    public TaskCreationResponseModel prepareSuccessView(TaskCreationResponseModel response) {
        return response;
    }

    /**
     * Prepare a fail view if Task creation was unsuccessful
     * @param error - the error
     */
    @Override
    public TaskCreationResponseModel prepareFailView(String error) {
        throw new TaskCreationFailed(error);
    }
}

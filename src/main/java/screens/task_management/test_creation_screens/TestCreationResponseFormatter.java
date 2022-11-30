package screens.task_management.test_creation_screens;

import use_cases.task_management.task_creation_use_case.TaskCreationPresenter;
import use_cases.task_management.task_creation_use_case.TaskCreationResponseModel;

public class TestCreationResponseFormatter implements TaskCreationPresenter {
    /**
     * Prepare a success view for the successful Test creation
     * @param response - response model for Task creation
     * @return - the response model
     */
    @Override
    public TaskCreationResponseModel prepareSuccessView(TaskCreationResponseModel response) {
        return response;
    }

    /**
     * Prepare a fail view if Test creation was unsuccessful
     * @param error - the error
     */
    @Override
    public TaskCreationResponseModel prepareFailView(String error) {
        throw new TestCreationFailed(error);
    }
}

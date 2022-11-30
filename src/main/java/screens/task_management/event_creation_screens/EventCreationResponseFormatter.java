package screens.task_management.event_creation_screens;

import use_cases.task_management.task_creation_use_case.TaskCreationPresenter;
import use_cases.task_management.task_creation_use_case.TaskCreationResponseModel;

public class EventCreationResponseFormatter implements TaskCreationPresenter {
    /**
     * Prepare a success view for a successful Event creation
     * @param response - response model for Task creation
     * @return - response model
     */
    @Override
    public TaskCreationResponseModel prepareSuccessView(TaskCreationResponseModel response) {
        return response;
    }

    /**
     * Prepare a failure view for an unsuccessful Event creation
     * @param error - error in Event creation
     * @return - response model
     */
    @Override
    public TaskCreationResponseModel prepareFailView(String error) {
        throw new EventCreationFailed(error);
    }
}

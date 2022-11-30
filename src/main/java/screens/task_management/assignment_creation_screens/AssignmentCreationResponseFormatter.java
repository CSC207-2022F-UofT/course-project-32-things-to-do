package screens.task_management.assignment_creation_screens;

import use_cases.task_management.task_creation_use_case.TaskCreationPresenter;
import use_cases.task_management.task_creation_use_case.TaskCreationResponseModel;

public class AssignmentCreationResponseFormatter implements TaskCreationPresenter {
    /**
     * Prepare a success view after creating an Assignment
     * @param response - response model for task creation
     * @return - the response model
     */
    @Override
    public TaskCreationResponseModel prepareSuccessView(TaskCreationResponseModel response) {
        return response;
    }

    /**
     * Prepare a failure view when Assignment creation is unsuccessful
     * @param error - the error in Assignment creation
     * @return - response model
     */
    @Override
    public TaskCreationResponseModel prepareFailView(String error) {
        throw new AssignmentCreationFailed(error);
    }
}

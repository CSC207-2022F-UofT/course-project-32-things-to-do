package screens.task_management.task_edit_delete_screens;

import use_cases.task_management.task_edit_use_case.TaskEditPresenter;
import use_cases.task_management.task_edit_use_case.TaskEditResponseModel;

public class TaskEditResponseFormatter implements TaskEditPresenter {
    /**
     * Prepare a success view after editing a Task
     * @param responseModel - response model for Task editing
     * @return - the response model
     */
    @Override
    public TaskEditResponseModel prepareSuccessView(TaskEditResponseModel responseModel) {
        return responseModel;
    }

    /**
     * Prepare a failure view when Task editing is unsuccessful
     * @param error - the error in Task editing
     * @return - throw a TaskEditFailed error
     */
    @Override
    public TaskEditResponseModel prepareFailView(String error) {
        throw new TaskEditFailed(error);
    }
}

package use_cases.task_management.task_edit_use_case;

public interface TaskEditPresenter {
    /**
     * Prepare a success view for when editing is successful
     * @param responseModel - response model for Task editing
     * @return - response model
     */
    TaskEditResponseModel prepareSuccessView(TaskEditResponseModel responseModel);

    /**
     * Prepare a fail view for when editing fails
     * @param error - error message
     * @return - throw a new TaskEditFailed error
     */
    TaskEditResponseModel prepareFailView(String error);
}

package use_cases.task_management.task_deletion_use_case;

public interface TaskDeletionPresenter {
    /**
     * Prepare a success view for when deletion is successful
     * @param t - the response model for Task deletion
     * @return - response model
     */
    TaskDeletionResponseModel prepareSuccessView(TaskDeletionResponseModel t);
}

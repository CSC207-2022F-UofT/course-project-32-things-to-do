package use_cases.task_management.task_deletion_use_case;

public interface TaskDeletionPresenter {
    TaskDeletionResponseModel prepareSuccessView(TaskDeletionResponseModel t);
    TaskDeletionResponseModel prepareFailureView(String error);
}

package screens.task_management.task_edit_delete_screens;

import use_cases.task_management.task_deletion_use_case.TaskDeletionPresenter;
import use_cases.task_management.task_deletion_use_case.TaskDeletionResponseModel;

public class TaskDeletionResponseFormatter implements TaskDeletionPresenter {
    @Override
    public TaskDeletionResponseModel prepareSuccessView(TaskDeletionResponseModel t) {
        return t;
    }

    @Override
    public TaskDeletionResponseModel prepareFailureView(String error) {
        throw new TaskDeletionFailed(error);
    }
}

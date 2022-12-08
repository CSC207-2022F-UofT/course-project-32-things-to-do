package screens.task_management.task_edit_delete_screens;

import use_cases.task_management.task_deletion_use_case.*;

public class TaskDeletionResponseFormatter implements TaskDeletionPresenter {
    /**
     * Prepare a success view for when Task deletion is successful
     * @param t - the response model for Task deletion
     * @return - response model
     */
    @Override
    public TaskDeletionResponseModel prepareSuccessView(TaskDeletionResponseModel t) {
        return t;
    }
}

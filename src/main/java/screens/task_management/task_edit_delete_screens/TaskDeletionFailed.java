package screens.task_management.task_edit_delete_screens;

public class TaskDeletionFailed extends RuntimeException {
    public TaskDeletionFailed(String error) {
        super(error);
    }
}

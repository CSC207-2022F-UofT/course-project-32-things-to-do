package screens.task_management.task_edit_delete_screens;

public class TaskEditFailed extends RuntimeException {
    public TaskEditFailed(String error) {
        super(error);
    }
}

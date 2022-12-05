package screens.task_management.task_edit_delete_screens;

public class TaskEditFailed extends RuntimeException {
    /**
     * An error for when Task editing doesn't work
     * @param error - error message
     */
    public TaskEditFailed(String error) {
        super(error);
    }
}

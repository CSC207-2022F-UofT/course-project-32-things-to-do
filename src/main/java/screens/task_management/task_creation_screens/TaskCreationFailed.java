package screens.task_management.task_creation_screens;

public class TaskCreationFailed extends RuntimeException {
    /**
     * An error for when task creation is unsuccessful
     * @param error - the error message
     */
    public TaskCreationFailed(String error) {
        super(error);
    }
}

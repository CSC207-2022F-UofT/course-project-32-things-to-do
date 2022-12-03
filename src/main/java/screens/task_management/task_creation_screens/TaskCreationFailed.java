package screens.task_management.task_creation_screens;

public class TaskCreationFailed extends RuntimeException {
    public TaskCreationFailed(String error) {
        super(error);
    }
}

package screens.collaborative_task_management.collaborative_task_creation_screens;

public class CollaborativeTaskCreationFailed extends RuntimeException{
    /**
     * An error for when a collaborative task creation is unsuccessful.
     * @param error - the error message.
     */
    public CollaborativeTaskCreationFailed(String error) { super(error); }
}

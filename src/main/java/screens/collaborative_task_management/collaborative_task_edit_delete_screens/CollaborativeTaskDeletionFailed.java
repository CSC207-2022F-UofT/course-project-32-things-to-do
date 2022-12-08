package screens.collaborative_task_management.collaborative_task_edit_delete_screens;

public class CollaborativeTaskDeletionFailed extends RuntimeException{
    /**
     * An error for when Collaborative Task deletion doesn't work
     * @param error - error message
     */
    public CollaborativeTaskDeletionFailed (String error) {super(error); }
}

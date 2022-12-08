package screens.collaborative_task_management.collaborative_task_edit_delete_screens;

public class CollaborativeTaskEditFailed extends RuntimeException{
    /**
     * An error for when Collaborative Task editing doesn't work
     * @param error - error message
     */
    public CollaborativeTaskEditFailed(String error) {super(error); }
}

package screens.task_management.assignment_creation_screens;

public class AssignmentCreationFailed extends RuntimeException {
    public AssignmentCreationFailed(String error) {
        super(error);
    }
}

package use_cases.task_management.task_deletion_use_case;

public class TaskDeletionResponseModel {
    private final String title;

    /**
     * A response model for Task deletion
     * @param title - the title of the Task
     */
    public TaskDeletionResponseModel(String title) {
        this.title = title;
    }

    // getter
    public String getTitle() {
        return this.title;
    }
}

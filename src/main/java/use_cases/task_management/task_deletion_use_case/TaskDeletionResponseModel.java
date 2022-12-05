package use_cases.task_management.task_deletion_use_case;

public class TaskDeletionResponseModel {
    private final String title;
    private final String id;

    /**
     * A response model for Task deletion
     * @param title - the title of the Task
     * @param id - the ID of the Task
     */
    public TaskDeletionResponseModel(String title, String id) {
        this.title = title;
        this.id = id;
    }

    // getters
    public String getTitle() {
        return this.title;
    }
    public String getId() {
        return this.id;
    }
}

package use_cases.task_management.task_creation_use_case;

// abstract request model allows task creation to be complete in one use case rather than 3
public abstract class TaskCreationRequestModel {
    private final String title;
    private final int priority;

    /**
     * A request model for Task creation
     * @param title - title of Task
     * @param priority - priority of Task
     */
    public TaskCreationRequestModel(String title, int priority) {
        this.title = title;
        this.priority = priority;
    }
    // getters
    public String getTitle() {
        return this.title;
    }
    public int getPriority() {
        return this.priority;
    }
}

package task_creation_use_case;

// abstract request model allows task creation to be complete in one use case rather than 3
public abstract class TaskCreationRequestModel {
    private String title;
    private int priority;
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

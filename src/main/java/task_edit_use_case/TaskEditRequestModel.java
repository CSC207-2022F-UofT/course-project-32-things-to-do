package task_edit_use_case;

public abstract class TaskEditRequestModel {
    private String title;
    private int priority;

    /**
     * Create a request model for a Task
     * @param title - title of Task being created
     * @param priority - priority of Task being created
     */
    public TaskEditRequestModel(String title, int priority) {
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

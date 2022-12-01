package use_cases.task_management.task_edit_use_case;

public abstract class TaskEditRequestModel {
    private String id;
    private boolean complete;
    private String title;
    private int priority;

    /**
     * Create a request model for a Task
     * @param id - the unique ID for the Task
     * @param complete - whether the Task is complete
     * @param title - title of Task being created
     * @param priority - priority of Task being created
     */
    public TaskEditRequestModel(String id, boolean complete, String title, int priority) {
        this.id = id;
        this.complete = complete;
        this.title = title;
        this.priority = priority;
    }

    // getters
    public boolean getComplete() {
        return this.complete;
    }
    public String getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public int getPriority() {
        return this.priority;
    }
}

package use_cases.task_management.task_edit_use_case;

public abstract class TaskEditRequestModel {
    private final String id;
    private final boolean complete;
    private final int priority;

    /**
     * Create a request model for a Task
     * @param id - the unique ID for the Task
     * @param complete - whether the Task is complete
     * @param priority - priority of Task being created
     */
    public TaskEditRequestModel(String id, boolean complete, int priority) {
        this.id = id;
        this.complete = complete;
        this.priority = priority;
    }

    // getters
    public boolean getComplete() {
        return this.complete;
    }
    public String getId() {
        return this.id;
    }
    public int getPriority() {
        return this.priority;
    }
}

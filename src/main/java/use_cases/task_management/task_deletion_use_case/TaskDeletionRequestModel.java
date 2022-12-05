package use_cases.task_management.task_deletion_use_case;

public class TaskDeletionRequestModel {
    private final String taskId;

    /**
     * A request model for Task deletion
     * @param taskId - the ID of the Task
     */
    public TaskDeletionRequestModel(String taskId) {
        this.taskId = taskId;
    }

    // getters
    public String getTaskId() {
        return this.taskId;
    }
}

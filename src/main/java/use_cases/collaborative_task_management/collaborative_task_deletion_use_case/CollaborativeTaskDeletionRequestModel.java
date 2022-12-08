package use_cases.collaborative_task_management.collaborative_task_deletion_use_case;

public class CollaborativeTaskDeletionRequestModel {
    private final String taskId;

    /**
     * A request model for Collaborative Task deletion
     * @param taskId - the ID of the Collaborative Task.
     */
    public CollaborativeTaskDeletionRequestModel(String taskId) { this.taskId = taskId; }

    public String getTaskId() { return this.taskId; }
}

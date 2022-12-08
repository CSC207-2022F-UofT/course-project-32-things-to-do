package use_cases.task_management.task_deletion_use_case;

public interface TaskDeletionInputBoundary {
    /**
     * Attempt to delete a Task
     *
     * @param requestModel - request model for deletion
     */
    void delete(TaskDeletionRequestModel requestModel);
}

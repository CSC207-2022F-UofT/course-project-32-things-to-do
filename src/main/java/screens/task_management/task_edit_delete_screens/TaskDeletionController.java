package screens.task_management.task_edit_delete_screens;

import use_cases.task_management.task_deletion_use_case.*;

public class TaskDeletionController {
    final TaskDeletionInputBoundary input;

    /**
     * A controller for interacting with the Task deletion use case
     * @param input - input boundary for deletion
     */
    public TaskDeletionController(TaskDeletionInputBoundary input) {
        this.input = input;
    }

    /**
     * Attempt to delete a Task
     *
     * @param taskId - the ID of the Task
     */
    public void delete(String taskId) {
        // create request model
        TaskDeletionRequestModel requestModel = new TaskDeletionRequestModel(taskId);

        // delete Task
        input.delete(requestModel);
    }
}

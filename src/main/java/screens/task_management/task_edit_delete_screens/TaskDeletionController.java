package screens.task_management.task_edit_delete_screens;

import use_cases.task_management.task_deletion_use_case.TaskDeletionRequestModel;
import use_cases.task_management.task_deletion_use_case.TaskDeletionResponseModel;
import use_cases.task_management.task_deletion_use_case.TaskDeletionInputBoundary;

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
     * @param taskId - the ID of the Task
     * @return - response model (input boundary will indicate success/failure)
     */
    public TaskDeletionResponseModel delete(String taskId) {
        // create request model
        TaskDeletionRequestModel requestModel = new TaskDeletionRequestModel(taskId);

        // delete Task
        return input.delete(requestModel);
    }
}

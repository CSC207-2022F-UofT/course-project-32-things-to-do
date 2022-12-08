package screens.collaborative_task_management.collaborative_task_edit_delete_screens;

import use_cases.collaborative_task_management.collaborative_task_deletion_use_case.CollaborativeTaskDeletionRequestModel;
import use_cases.collaborative_task_management.collaborative_task_deletion_use_case.CollaborativeTaskDeletionResponseModel;
import use_cases.collaborative_task_management.collaborative_task_deletion_use_case.CollaborativeTaskDeletionInputBoundary;

import java.io.IOException;

public class CollaborativeTaskDeletionController {
    final CollaborativeTaskDeletionInputBoundary input;

    /**
     * A controller for interacting with the Collaborative Task deletion use case
     * @param input - input boundary for deletion
     */
    public CollaborativeTaskDeletionController(CollaborativeTaskDeletionInputBoundary input) { this.input = input; };

    /**
     * Attempt to delete a Collaborative Task
     * @param taskId - the ID of the Collaborative Task
     * @return - response model (input boundary will indicate success/failure)
     */
    public CollaborativeTaskDeletionResponseModel delete(String taskId) {
        CollaborativeTaskDeletionRequestModel requestModel = new CollaborativeTaskDeletionRequestModel(taskId);

        return input.delete(requestModel);
    }
}

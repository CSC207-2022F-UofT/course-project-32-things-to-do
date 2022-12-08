package screens.collaborative_task_management.collaborative_task_edit_delete_screens;

import use_cases.collaborative_task_management.collaborative_task_edit_use_case.CollaborativeTaskEditRequestModel;
import use_cases.collaborative_task_management.collaborative_task_edit_use_case.CollaborativeTaskEditInputBoundary;
import use_cases.collaborative_task_management.collaborative_task_edit_use_case.CollaborativeTaskEditResponseModel;

import java.io.IOException;
import java.time.LocalDateTime;

public class CollaborativeTaskEditController {
    final CollaborativeTaskEditInputBoundary input;

    /**
     * A controller for interacting with the Collaborative Task edit use case
     * @param input - the input boundary for editing
     */
    public CollaborativeTaskEditController(CollaborativeTaskEditInputBoundary input) { this.input = input; }

    /**
     * Attempt to edit a Collaborative Task
     * @param complete - whether the Collaborative Task is complete
     * @param id - the ID of the Collaborative Task (for retrieval purposes)
     * @param priority - the priority of the Collaborative Task
     * @param recurring - whether the Collaborative Task is recurring
     * @param frequency - the frequency of the Collaborative Task if recurring
     * @param startTime - the Collaborative Task's start time
     * @param endTime - the Collaborative Task's end time
     * @param deadline - the Collaborative Task's deadline
     * @return - response model (input boundary will indicate success/failure)
     */
    public CollaborativeTaskEditResponseModel edit(boolean complete, String id, int priority, boolean recurring, String frequency, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime deadline) throws IOException{
        CollaborativeTaskEditRequestModel requestModel = new CollaborativeTaskEditRequestModel(id, complete, priority, recurring, frequency, startTime, endTime, deadline);
        return input.edit(requestModel);
    }
}

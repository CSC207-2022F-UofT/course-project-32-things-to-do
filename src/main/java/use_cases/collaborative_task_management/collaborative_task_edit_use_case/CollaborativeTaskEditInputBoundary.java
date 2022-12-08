package use_cases.collaborative_task_management.collaborative_task_edit_use_case;

public interface CollaborativeTaskEditInputBoundary {
    /**
     * Attempt to edit a Collaborative Task
     * @param requestModel - request model for editing
     * @return - response model
     */
    CollaborativeTaskEditResponseModel edit(CollaborativeTaskEditRequestModel requestModel);
}
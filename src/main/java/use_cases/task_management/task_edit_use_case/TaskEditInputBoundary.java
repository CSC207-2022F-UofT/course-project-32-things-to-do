package use_cases.task_management.task_edit_use_case;

public interface TaskEditInputBoundary {
    /**
     * Attempt to edit a Task
     * @param requestModel - the request model of the Task being edited
     * @param type - type of Task
     * @return - response model
     */
    TaskEditResponseModel edit(TaskEditRequestModel requestModel, String type);
}

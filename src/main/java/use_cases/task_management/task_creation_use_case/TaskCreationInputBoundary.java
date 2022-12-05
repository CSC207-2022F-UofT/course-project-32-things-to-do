package use_cases.task_management.task_creation_use_case;

public interface TaskCreationInputBoundary {
    /**
     * Attempt to create a  Task
     * @param requestModel - the request model for creation
     * @param type - the type of Task being created ("Test", "Assignment", "Event")
     * @return - response model
     */
    TaskCreationResponseModel create(TaskCreationRequestModel requestModel, String type);
}

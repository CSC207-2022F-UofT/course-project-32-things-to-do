package use_cases.task_management.task_edit_use_case;

public interface TaskEditInputBoundary {
    TaskEditResponseModel edit(TaskEditRequestModel requestModel, String type);
}
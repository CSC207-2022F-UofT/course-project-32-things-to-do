package task_edit_use_case;

public interface TaskEditInputBoundary {
    TaskEditResponseModel edit(TaskEditRequestModel requestModel, String type);
}

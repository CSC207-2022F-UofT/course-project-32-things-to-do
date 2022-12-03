package use_cases.task_management.task_edit_use_case;

public interface TaskEditPresenter {
    TaskEditResponseModel prepareSuccessView(TaskEditResponseModel responseModel);
    TaskEditResponseModel prepareFailView(String error);
}

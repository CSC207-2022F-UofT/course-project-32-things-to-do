package use_cases.task_management.task_creation_use_case;

public interface TaskCreationPresenter {
    TaskCreationResponseModel prepareSuccessView(TaskCreationResponseModel response);
    TaskCreationResponseModel prepareFailView(String error);
}

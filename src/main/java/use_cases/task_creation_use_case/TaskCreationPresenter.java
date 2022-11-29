package use_cases.task_creation_use_case;

public interface TaskCreationPresenter {
    TaskCreationResponseModel prepareSuccessView(TaskCreationResponseModel response);
    TaskCreationResponseModel prepareFailView(String error);
}

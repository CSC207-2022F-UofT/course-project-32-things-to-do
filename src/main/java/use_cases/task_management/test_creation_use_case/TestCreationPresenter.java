package use_cases.task_management.test_creation_use_case;

public interface TestCreationPresenter {
    TestCreationResponseModel prepareSuccessView(TestCreationResponseModel response);
    TestCreationResponseModel prepareFailView(String error);
}

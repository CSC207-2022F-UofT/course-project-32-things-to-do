package use_cases.task_management.test_edit_use_case;

public interface TestEditPresenter {
    TestEditResponseModel prepareSuccessView(TestEditResponseModel responseModel);
    TestEditResponseModel prepareFailView(String error);
}

package use_cases.task_management.test_edit_use_case;

public class TestEditInteractor implements TestEditInputBoundary {
    TestEditPresenter presenter;
    public TestEditInteractor(TestEditPresenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public TestEditResponseModel edit(TestEditRequestModel requestModel) {
        // todo -- account for errors in input

        requestModel.getTest().setTitle(requestModel.getTitle());
        requestModel.getTest().setPriority(requestModel.getPriority());
        requestModel.getTest().setTimeBlock(requestModel.getStartTime(), requestModel.getEndTime());
        requestModel.getTest().setWeightage(requestModel.getWeightage());

        TestEditResponseModel response = new TestEditResponseModel(requestModel.getTitle());
        return presenter.prepareSuccessView(response);
    }
}

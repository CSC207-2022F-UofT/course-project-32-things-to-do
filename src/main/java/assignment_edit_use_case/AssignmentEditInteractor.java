package assignment_edit_use_case;

public class AssignmentEditInteractor implements AssignmentEditInputBoundary {
    AssignmentEditPresenter presenter;
    public AssignmentEditInteractor(AssignmentEditPresenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public AssignmentEditResponseModel edit(AssignmentEditRequestModel requestModel) {
        // todo -- account for incorrect formatting & whatnot

        requestModel.getAssignment().setTitle(requestModel.getTitle());
        requestModel.getAssignment().setPriority(requestModel.getPriority());
        requestModel.getAssignment().setDueDate(requestModel.getDueDate());
        requestModel.getAssignment().setWeightage(requestModel.getWeightage());

        AssignmentEditResponseModel response = new AssignmentEditResponseModel(requestModel.getTitle());
        return presenter.prepareSuccessView(response);
    }
}

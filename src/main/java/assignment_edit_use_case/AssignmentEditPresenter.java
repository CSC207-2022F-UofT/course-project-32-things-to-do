package assignment_edit_use_case;

public interface AssignmentEditPresenter {
    AssignmentEditResponseModel prepareSuccessView(AssignmentEditResponseModel responseModel);
    AssignmentEditResponseModel prepareFailView(String error);
}

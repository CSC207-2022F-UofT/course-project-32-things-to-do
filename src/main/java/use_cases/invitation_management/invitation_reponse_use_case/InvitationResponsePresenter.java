package use_cases.invitation_management.invitation_reponse_use_case;


public interface InvitationResponsePresenter {
    /**
     * The method implemented in the presenter that prepares the success view when there are no errors.
     *
     * @param responseModel - an InvitationResponseResponseModel
     * @return an InvitationResponseResponseModel
     */
    InvitationResponseResponseModel prepareSuccessView(InvitationResponseResponseModel responseModel);

    /**
     * The method implemented in the presenter that prepares the fail view for when there are errors.
     *
     * @param error - the type of error that happened
     * @return an InvitationResponseResponseModel
     */
    InvitationResponseResponseModel prepareFailView(String error);
}

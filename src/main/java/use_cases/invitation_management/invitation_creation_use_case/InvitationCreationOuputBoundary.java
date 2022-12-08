package use_cases.invitation_management.invitation_creation_use_case;

/**
 * Output Boundary for the Invitation Creation Use Case
 * (inverts dependency for interactor to presenter)
 */

public interface InvitationCreationOuputBoundary {
    /**
     * The method implemented in the presenter that prepares the success view when there are no errors.
     *
     * @param responseModel - an InvitationCreationResponseModel
     * @return an InvitationCreationResponseModel
     */
    InvitationCreationResponseModel prepareSuccessView(InvitationCreationResponseModel responseModel);

    /**
     * The method implemented in the presenter that prepares the fail view for when there are errors.
     *
     * @param error - the type of error that happened
     * @return an InvitationCreationResponseModel
     */
    InvitationCreationResponseModel prepareFailView(String error);
}

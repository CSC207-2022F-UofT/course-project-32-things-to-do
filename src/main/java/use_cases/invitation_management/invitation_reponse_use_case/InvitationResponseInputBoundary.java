package use_cases.invitation_management.invitation_reponse_use_case;

public interface InvitationResponseInputBoundary {
    /**
     * Attempt to respond to an Invitation
     * @param requestModel - the request model for responding
     * @return - response model
     */
    InvitationResponseResponseModel respond(InvitationResponseRequestModel requestModel);
}



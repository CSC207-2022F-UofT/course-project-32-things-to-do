package use_cases.invitation_management.invitation_creation_use_case;

public interface InvitationCreationInputBoundary {
    /**
     * Attempt to create an Invitation
     * @param requestModel - the request model for creation
     * @return - response model
     */
    InvitationCreationResponseModel create(InvitationCreationRequestModel requestModel);
}


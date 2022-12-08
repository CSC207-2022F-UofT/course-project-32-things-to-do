package use_cases.invitation_management.invitation_reponse_use_case;

/**
 * Response Model for the Invitation Response Use Case
 * Acts as the output data object in the use case layer
 */

public class InvitationResponseResponseModel {
    private final boolean accept;

    /**
     * A response model for successful Invitation response.
     * @param accept - whether the invitation was accepted.
     */
    public InvitationResponseResponseModel(boolean accept) {
        this.accept = accept;
    }

    public boolean getAccept(){ return this.accept; }
}

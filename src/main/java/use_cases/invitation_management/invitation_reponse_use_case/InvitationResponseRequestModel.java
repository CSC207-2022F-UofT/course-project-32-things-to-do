package use_cases.invitation_management.invitation_reponse_use_case;

/**
 * Request Model for the Invitation Response Use Case
 * Acts as the input data object in the use case layer
 */

public class InvitationResponseRequestModel {
    private final String collaborativeId;
    private final boolean accept;
    /**
     * A request model for Invitation response
     * @param accept - whether the invitation was accepted.
     */
    public InvitationResponseRequestModel(String collaborativeId, boolean accept) {
        this.collaborativeId = collaborativeId;
        this.accept = accept;
    }

    public String getCollaborativeId(){ return this.collaborativeId; }

    public boolean getAccept() {return this.accept; }
}

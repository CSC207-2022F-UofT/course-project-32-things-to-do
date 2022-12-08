package use_cases.invitation_management.invitation_creation_use_case;

/**
 * Request Model for the Invitation Creation Use Case
 * Acts as the input data object in the use case layer
 */

public class InvitationCreationRequestModel {
    private final String recieverUsername;
    private final String collaborativeId;
    /**
     * A request model for Invitation creation
     * @param recieverUsername - the usernmae of the Student User who is receiving the invitation.
     * @param collaborativeId - the id of the collaborative task the invitation refers to.
     */
    public InvitationCreationRequestModel(String recieverUsername, String collaborativeId) {
        this.recieverUsername = recieverUsername;
        this.collaborativeId = collaborativeId;
    }

    public String getRecieverUsername() { return this.recieverUsername; }

    public String getCollaborativeId(){ return this.collaborativeId; }
}
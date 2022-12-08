package use_cases.invitation_management.invitation_creation_use_case;

/**
 * Response Model for the Invitation Creation Use Case
 * Acts as the output data object in the use case layer
 */

public class InvitationCreationResponseModel {
    private final String recieverUsername;
    private final String collaborativeId;

    /**
     * A response model for successful Invitation creation.
     * @param recieverUsername - the usernmae of the Student User who is receiving the invitation.
     * @param collaborativeId - the id of the invitation's collaborative task.
     */
    public InvitationCreationResponseModel(String recieverUsername, String collaborativeId) {
        this.recieverUsername = recieverUsername;
        this.collaborativeId = collaborativeId;
    }

    public String getRecieverUsername() { return this.recieverUsername; }

    public String getCollaborativeId(){ return this.collaborativeId; }
}
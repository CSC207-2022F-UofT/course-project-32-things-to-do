package entities;

public class Invitation {
    private final CollaborativeTask collaborativeTask;
    private final StudentUser sender;
    private final StudentUser receiver;
    private String status;

    /**
     * Create an invitation.
     * @param collaborativeTask - the CollaborativeTask to which the receiver is being invited to join.
     * @param sender - the StudentUser sending the invitation.
     * @param receiver - the StudentUser receiving the invitation.
     */
    public Invitation(CollaborativeTask collaborativeTask, StudentUser sender, StudentUser receiver) {
        this.collaborativeTask = collaborativeTask;
        this.sender = sender;
        this.receiver = receiver;
        this.status = "Pending";
    }

    /**
     * Accept or decline an invitation.
     * @param accepted - the receiver's answer to the invitation.
     */
    protected void setStatus(boolean accepted){
        if(accepted){
            this.status = "Accepted";
        }
        else{
            this.status = "Declined";
        }
    }

    /**
     * @return the invitation's status ("Pending", "Accepted", or "Declined")
     */
    protected String getStatus(){
        return this.status;
    }

    /**
     * @return the Collaborative Task to which the invitation is referring to.
     */
    protected CollaborativeTask getCollaborativeTask(){
        return this.collaborativeTask;
    }

    /**
     * @return the sender of the invitation.
     */
    protected StudentUser getSender(){
        return this.sender;
    }

    /**
     * @return the receiver of the invitation,
     */
    protected StudentUser getReceiver(){
        return this.receiver;
    }

    /**
     *
     * Cancel an Invitation by removing it from a user's inbox.
     * @return - whether the Invitation has been successfully canceled.
     */
    protected boolean cancel() {
        return true;
    }

    /**
     * Send an Invitation by adding it to a user's inbox.
     * @return - whether the Collaborative Task has been successfully sent.
     */
    protected boolean send() {
        return true;
    }

}

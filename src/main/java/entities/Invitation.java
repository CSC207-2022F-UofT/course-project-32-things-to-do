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
    public void setStatus(boolean accepted){
        if(accepted){ this.status = "Accepted"; }
        else{ this.status = "Declined"; }
    }

    public String getStatus(){
        return this.status;
    }

    public CollaborativeTask getCollaborativeTask(){
        return this.collaborativeTask;
    }

    public StudentUser getSender(){ return this.sender; }

    public StudentUser getReceiver(){ return this.receiver; }
}

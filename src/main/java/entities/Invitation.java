package entities;

import java.util.ArrayList;

public class Invitation {
    private CollaborativeTask collaborativeTask;
    private StudentUser sender;
    private ArrayList<StudentUser> receivers;
    private String status;

    /**
     * Create an invitation.
     * @param collaborativeTask - the CollaborativeTask to which the receiver is being invited to join.
     * @param sender - the StudentUser sending the invitation.
     * @param receivers - the StudentUsers receiving the invitation.
     */
    public Invitation(CollaborativeTask collaborativeTask, StudentUser sender, ArrayList<StudentUser> receivers) {
        this.collaborativeTask = collaborativeTask;
        this.sender = sender;
        this.receivers = receivers;
        this.status = "Pending";
    }
}

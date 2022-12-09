package use_cases.invitation_management.invitation_reponse_use_case;

import entities.CollaborativeTask;
import entities.StudentUser;

public interface InvitationResponseDisplayer {
    CollaborativeTask getCollaborativeTask();
    StudentUser getSender();
    StudentUser getReceiver();
    String getStatus();
}

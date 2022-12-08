package use_cases.invitation_management.invitation_creation_use_case;
import entities.*;
import use_cases.login_registration.user_register_usecase.StudentSaveRequest;
import use_cases.login_registration.user_register_usecase.UserRegGateway;
import use_cases.login_registration.user_register_usecase.UserRegSaveRequest;
import java.util.HashMap;

/**
 * Invitation Creation Use Case Interactor (use case layer)
 * Implements business logic on entities
 */

public class InvitationCreationInteractor implements InvitationCreationInputBoundary {
    private final InvitationCreationOuputBoundary outputBoundary;
    private final UserRegGateway userGateway;
    private final HashMap<String, UserRegSaveRequest> accounts;
    private final StudentUser sender = (StudentUser) CurrentUser.getCurrentUser();

    /**
     * Interactor for Invitation Creation.
     * @param outputBoundary - the output boundary for displaying results.
     */
    public InvitationCreationInteractor(InvitationCreationOuputBoundary outputBoundary, UserRegGateway userGateway, HashMap<String, UserRegSaveRequest> accounts) {
        this.outputBoundary = outputBoundary;
        this.userGateway = userGateway;
        this.accounts = accounts;
    }

    /**
     * Create an Invitation
     * @param requestModel - request model for Invitation
     * @return - response model after Invitation is created
     */
    public InvitationCreationResponseModel create(InvitationCreationRequestModel requestModel) {
        StudentUser receiver = ((StudentSaveRequest)accounts.get(requestModel.getRecieverUsername())).initializeUser();
        CollaborativeTask collaborativeTask = (CollaborativeTask) TaskMap.findTask(requestModel.getCollaborativeId());

        if (!(userGateway.existsByName(requestModel.getRecieverUsername()))) return outputBoundary.prepareFailView("Please enter a registered student user.");
        else if (requestModel.getRecieverUsername().equals(sender.getName())) return outputBoundary.prepareFailView("Please enter user other than yourself.");
        else if (collaborativeTask.getTeammates().contains(receiver) || collaborativeTask.getPendingTeammates().contains(receiver)) return outputBoundary.prepareFailView("Please enter a student who has not already been invited.");

        Invitation newInvitation = new Invitation(collaborativeTask, sender, receiver);
        receiver.getInbox().add(newInvitation);

        collaborativeTask.getPendingTeammates().add(receiver);

        // display success to user
        InvitationCreationResponseModel response = new InvitationCreationResponseModel(requestModel.getRecieverUsername(), requestModel.getCollaborativeId());
        return outputBoundary.prepareSuccessView(response);
    }
}



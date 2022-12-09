package use_cases.invitation_management.invitation_reponse_use_case;
import entities.*;

/**
 * Invitation Response Use Case Interactor (use case layer)
 * Implements business logic on entities
 */
public class InvitationResponseInteractor implements InvitationResponseInputBoundary {
    private final InvitationResponsePresenter presenter;
    private final StudentUser receiver = (StudentUser) CurrentUser.getCurrentUser();

    /**
     * Interactor for Invitation Response.
     * @param presenter - displays success/fail views
     */
    public InvitationResponseInteractor(InvitationResponsePresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Attempt to respond to an invitation.
     * @param requestModel - request model of the Invitation being responded to.
     * @return - response model after Invitation is created
     */
    @Override
    public InvitationResponseResponseModel respond(InvitationResponseRequestModel requestModel) {
        CollaborativeTask collaborativeTask = (CollaborativeTask) TaskMap.findTask(requestModel.getCollaborativeId());
        for (Invitation i : receiver.getInbox()){
            if (i.getCollaborativeTask().getId().equals(collaborativeTask.getId())){
                receiver.getInbox().remove(i);
                i.setStatus(requestModel.getAccept());
                if(!requestModel.getAccept()){
                    collaborativeTask.getPendingTeammates().remove(receiver);
                    collaborativeTask.getDeclinedTeammates().add(receiver);
                }
                else{
                    collaborativeTask.getPendingTeammates().remove(receiver);
                    collaborativeTask.getTeammates().add(receiver);
                    receiver.addTaskToList(collaborativeTask.getId());
//                    Already in TaskMap
//                    TaskMap.addTask(collaborativeTask.getId(), collaborativeTask);
                }
            }
        }

        // display success to user
        InvitationResponseResponseModel response = new InvitationResponseResponseModel(requestModel.getAccept());
        return presenter.prepareSuccessView(response);
    }
}



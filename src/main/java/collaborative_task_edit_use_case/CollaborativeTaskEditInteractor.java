package collaborative_task_edit_use_case;

import collaborative_task_creation_use_case.CollaborativeTaskCreationResponseModel;
import entities.CollaborativeTask;
import entities.StudentUser;

import java.util.ArrayList;

public class CollaborativeTaskEditInteractor implements CollaborativeTaskEditInputBoundary {
    private final CollaborativeTaskEditPresenter presenter;
    private final StudentUser student;

    public CollaborativeTaskEditInteractor(CollaborativeTaskEditPresenter collaborativeTaskPresenter, StudentUser student) {
        this.presenter = collaborativeTaskPresenter;
        this.student = student;
    }

    @Override
    public CollaborativeTaskEditResponseModel edit(CollaborativeTaskEditRequestModel requestModel) {
        if (requestModel.getTitle().equals("") || requestModel.getStartTime() == null || requestModel.getEndTime() == null || requestModel.getDeadline() == null || requestModel.getLeader() != student) {
            return presenter.prepareFailView("Please fill in all required information.");
        }
        requestModel.getCollaborativeTask().setTitle(requestModel.getTitle());
        requestModel.getCollaborativeTask().setPriority(requestModel.getPriority());
        requestModel.getCollaborativeTask().setTimeBlock(requestModel.getStartTime(), requestModel.getEndTime());
        requestModel.getCollaborativeTask().setDeadline(requestModel.getDeadline());
        requestModel.getCollaborativeTask().setRecurringAndFrequency(requestModel.getRecurring(), requestModel.getFrequency());
        requestModel.getCollaborativeTask().setTeammates(requestModel.getTeammates());
        requestModel.getCollaborativeTask().setLeader(requestModel.getLeader());


        CollaborativeTaskEditResponseModel collaborativeTaskEditResponseModel = new CollaborativeTaskEditResponseModel(requestModel.getTitle(), requestModel.getStartTime(), requestModel.getEndTime(), requestModel.getDeadline(), requestModel.getTeammates(), requestModel.getLeader());
        return presenter.prepareSuccessView(collaborativeTaskEditResponseModel);
    }
}
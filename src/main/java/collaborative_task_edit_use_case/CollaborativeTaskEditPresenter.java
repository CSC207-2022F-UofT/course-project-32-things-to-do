package collaborative_task_edit_use_case;

import entities.CollaborativeTask;

public interface CollaborativeTaskEditPresenter {
    CollaborativeTaskEditResponseModel prepareSuccessView(CollaborativeTaskEditResponseModel collaborativeTaskEditResponseModel);

    CollaborativeTaskEditResponseModel prepareFailView(String error);
}
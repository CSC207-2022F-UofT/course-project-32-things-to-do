package collaborative_task_creation_use_case;

import entities.CollaborativeTask;

public interface CollaborativeTaskCreationPresenter {
    CollaborativeTaskCreationResponseModel prepareSuccessView(CollaborativeTaskCreationResponseModel collaborativeTaskCreationResponseModel);

    CollaborativeTaskCreationResponseModel prepareFailView(String error);
}
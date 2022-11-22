package collaborative_task_creation_use_case;

import entities.CollaborativeTask;

public interface CollaborativeTaskCreationInputBoundary {
    CollaborativeTaskCreationResponseModel create(CollaborativeTaskCreationRequestModel requestModel);
}
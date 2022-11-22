package collaborative_task_edit_use_case;

import entities.CollaborativeTask;

public interface CollaborativeTaskEditInputBoundary {
    CollaborativeTaskEditResponseModel edit(CollaborativeTaskEditRequestModel requestModel);
}
package use_cases.collaborative_task_management.collaborative_task_creation_use_case;

import java.io.IOException;

public interface CollaborativeTaskCreationInputBoundary {
    CollaborativeTaskCreationResponseModel create(CollaborativeTaskCreationRequestModel requestModel) throws IOException;
}
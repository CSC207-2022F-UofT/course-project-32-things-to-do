package screens.collaborative_task_management.collaborative_task_creation_screens;

import use_cases.collaborative_task_management.collaborative_task_creation_use_case.CollaborativeTaskCreationInputBoundary;
import use_cases.collaborative_task_management.collaborative_task_creation_use_case.CollaborativeTaskCreationResponseModel;
import use_cases.collaborative_task_management.collaborative_task_creation_use_case.CollaborativeTaskCreationRequestModel;

import java.time.LocalDateTime;

public class CollaborativeTaskCreationController {
    final CollaborativeTaskCreationInputBoundary input;

    /**
     * A controller for interacting with the Collaborative Task creation use case
     * @param input - the input boundary for creating Collaborative Tasks
     */
    public CollaborativeTaskCreationController(CollaborativeTaskCreationInputBoundary input){ this.input = input; }

    /**
     * Attempt to create a Collaborative Task
     * @param title - the title of the Collaborative Task
     * @param priority - the priority of the Collaborative Task
     * @param recurring - whether the Collaborative task is recurring
     * @param frequency - the frequency of the Collaborative Task if it is recurring
     * @param startTime - the Collaborative Task's start time
     * @param endTime- the Collaborative Task's end time
     * @param deadline - the Collaborative Task's deadline
     * @return - response model (input boundary will indicate success/failure)
     */
    CollaborativeTaskCreationResponseModel create(String title, int priority, boolean recurring, String frequency, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime deadline) {
        CollaborativeTaskCreationRequestModel requestModel = new CollaborativeTaskCreationRequestModel(title, priority, recurring, frequency, startTime, endTime, deadline);
        return input.create(requestModel);
    }

}


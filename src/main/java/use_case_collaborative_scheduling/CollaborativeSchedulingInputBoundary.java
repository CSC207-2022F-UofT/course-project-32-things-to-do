package use_case_collaborative_scheduling;
import entities.TaskMap;

public interface CollaborativeSchedulingInputBoundary {
    CollaborativeSchedulingResponseModel schedule(CollaborativeSchedulingRequestModel requestModel, TaskMap allTasks);
}

package use_case_collaborative_scheduling;

public interface CollaborativeSchedulingOutputBoundary {

    // are you supposed to use CollaborativeSchedulingRequestModel??? what is the type supposed to be?
    // Instead of CollaborativeSchedulingRequestModel, have a different model??
    // the output boundary is implemented by the presenter
    //      what do I want to the presenter to return after this? different types of screens?
    CollaborativeSchedulingResponseModel prepareAvailableTimes(CollaborativeSchedulingResponseModel responseModel);

    CollaborativeSchedulingResponseModel prepareFailView(String error);
}

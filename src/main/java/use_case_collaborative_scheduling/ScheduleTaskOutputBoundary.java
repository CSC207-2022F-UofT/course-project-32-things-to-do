package use_case_collaborative_scheduling;

public interface ScheduleTaskOutputBoundary {

    ScheduleTaskResponseModel prepareSuccessView(ScheduleTaskResponseModel responseModel);

    ScheduleTaskResponseModel prepareFailView(String error);

}

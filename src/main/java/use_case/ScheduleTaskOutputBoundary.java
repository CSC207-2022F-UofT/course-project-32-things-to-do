package use_case;

public interface ScheduleTaskOutputBoundary {

    ScheduleTaskResponseModel prepareSuccessView(ScheduleTaskResponseModel responseModel);

    ScheduleTaskResponseModel prepareFailView(String error);

}

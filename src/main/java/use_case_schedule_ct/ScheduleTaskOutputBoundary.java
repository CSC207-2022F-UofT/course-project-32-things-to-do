package use_case_schedule_ct;

public interface ScheduleTaskOutputBoundary {

    ScheduleTaskResponseModel prepareSuccessView(ScheduleTaskResponseModel responseModel);

    ScheduleTaskResponseModel prepareFailView(String error);

}

package screens;

import use_case_schedule_ct.ScheduleTaskResponseModel;
import use_case_schedule_ct.ScheduleTaskOutputBoundary;

public class SchedulingTaskPresenter implements ScheduleTaskOutputBoundary{

    @Override
    public ScheduleTaskResponseModel prepareSuccessView(ScheduleTaskResponseModel outputData) {
        return outputData;
    }

    @Override
    public ScheduleTaskResponseModel prepareFailView(String error){
        throw new SchedulingTimesFailed(error);
    }
}

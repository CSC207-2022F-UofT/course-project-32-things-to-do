package screens;

import use_case.ScheduleTaskResponseModel;
import use_case.ScheduleTaskOutputBoundary;

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

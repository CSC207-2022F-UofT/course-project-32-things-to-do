package screens;

import use_case_collaborative_scheduling.ScheduleTaskResponseModel;
import use_case_collaborative_scheduling.ScheduleTaskOutputBoundary;

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

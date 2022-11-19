package use_case_schedule_ct;
import entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ScheduleTaskInteractor implements ScheduleTaskInputBoundary {

    // in this class, want to take the given time and add it to the calendar UI
    // from the view, we get information about the time(s) the user wants to schedule that gets passed down to t
    // the controller
    // bring that information

    // remove time block value from collaborative task instance attribute
    // add time block value to collaborative task instance attribute
    //      message Sima to change instance attribute of time block to ArrayList<ArrayList<LocalDateTime>>

    // returns successful input of time block - return to the view that the time changed
    private final ScheduleTaskOutputBoundary outputBoundary;

    public ScheduleTaskInteractor(ScheduleTaskOutputBoundary outputBoundary){
        this.outputBoundary = outputBoundary;
    }

    @Override
    public ScheduleTaskResponseModel schedule(ScheduleTaskRequestModel requestModel) {
        ArrayList<ArrayList<LocalDateTime>> times = requestModel.getScheduleTimes();
        CollaborativeTask task = requestModel.getTask();

        task.setTimeBlocks(times);

        ScheduleTaskResponseModel responseModel = new ScheduleTaskResponseModel(true);
        return outputBoundary.prepareSuccessView(responseModel);
    }

}

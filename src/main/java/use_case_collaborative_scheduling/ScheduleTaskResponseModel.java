package use_case_collaborative_scheduling;

public class ScheduleTaskResponseModel {

    private final boolean success;

    public ScheduleTaskResponseModel(boolean success) {
        this.success = success;
    }

    public boolean getSuccess(){
        return success;
    }
}

package use_cases.calendar_scheduler.scheduler_use_case;

import entities.Task;

import java.util.ArrayList;

public class SchedulerResponseModel {

    private boolean scheduleCancel;

    /**
     * Create a response model for the scheduling use case
     * @param scheduleCancel - whether the user wants to cancel scheduling
     */
    public SchedulerResponseModel(boolean scheduleCancel) {
        this.scheduleCancel = scheduleCancel;
    }

    public boolean isScheduleCancel() {
        return scheduleCancel;
    }

    public void setScheduleCancel(boolean scheduleCancel) {
        this.scheduleCancel = scheduleCancel;
    }
}

package use_cases.calendar_scheduler.schedule_conflict_use_case;

public class ScheduleConflictResponseModel {

    private final boolean scheduleWithConflict;

    /**
     * Response model for the schedule conflict use case
     * @param scheduleWithConflict - the user's choice on proceeding with scheduling
     */
    public ScheduleConflictResponseModel(boolean scheduleWithConflict) {
        this.scheduleWithConflict = scheduleWithConflict;
    }

    public boolean isScheduleWithConflict() {
        return scheduleWithConflict;
    }

}

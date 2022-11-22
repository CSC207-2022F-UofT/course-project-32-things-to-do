package schedule_conflict_use_case;

public class ScheduleConflictResponseModel {

    private boolean scheduleConflict;

    /**
     * Response model for the schedule conflict use case
     * @param scheduleConflict - the user's choice on proceeding with scheduling
     */
    public ScheduleConflictResponseModel(boolean scheduleConflict) {
        this.scheduleConflict = scheduleConflict;
    }

    public boolean isScheduleConflict() {
        return scheduleConflict;
    }

    public void setScheduleConflict(boolean scheduleConflict) {
        this.scheduleConflict = scheduleConflict;
    }
}

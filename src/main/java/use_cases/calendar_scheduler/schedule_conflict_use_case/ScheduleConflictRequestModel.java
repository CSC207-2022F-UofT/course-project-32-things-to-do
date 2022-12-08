package use_cases.calendar_scheduler.schedule_conflict_use_case;

import entities.*;

public class ScheduleConflictRequestModel {

    private final Task conflictingTask;

    /**
     * Request model for the schedule conflict use case
     * @param conflictingTask - the given conflicting task
     */
    public ScheduleConflictRequestModel(Task conflictingTask) {
        this.conflictingTask = conflictingTask;
    }

    public Task getConflictingTask() {
        return conflictingTask;
    }

}

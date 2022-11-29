package use_cases.calendar_scheduler.schedule_conflict_use_case;

import entities.Task;

public class ScheduleConflictRequestModel {

    private Task conflictingTask;

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

    public void setConflictingTask(Task conflictingTask) {
        this.conflictingTask = conflictingTask;
    }
}

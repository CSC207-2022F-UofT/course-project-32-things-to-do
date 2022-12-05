package use_cases.collaborative_task_scheduling.scheduling_ct_use_case;

/**
 * Request Model for the Scheduling Collaborative Tasks Use Case
 * Acts as the input data object in the use case layer
 */

public class ScheduleCTRequestModel {

    private final String taskName;

    private final String startTime;

    private final String endTime;

    public ScheduleCTRequestModel(String taskName, String startTime, String endTime) {
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTaskName() { return taskName; }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
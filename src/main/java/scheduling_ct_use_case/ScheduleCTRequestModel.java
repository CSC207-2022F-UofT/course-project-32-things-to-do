package scheduling_ct_use_case;

/**
 * Request Model for the Scheduling Collaborative Tasks Use Case
 * Acts as the input data object in the use case layer
 */

public class ScheduleCTRequestModel {

    private final String taskName;

    private final String username;

    private final String startTime;

    private final String endTime;

    public ScheduleCTRequestModel(String taskName, String username, String startTime, String endTime) {
        this.taskName = taskName;
        this.username = username;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTaskName() {
        return taskName;
    }
    public String getUsername() { return username; }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }


}

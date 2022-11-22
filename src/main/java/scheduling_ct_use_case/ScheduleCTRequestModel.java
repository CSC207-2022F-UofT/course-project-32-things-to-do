package scheduling_ct_use_case;

// use case layer

/*
 Notes:
 - requests what is needed for its input data (what person in front of computer enters)
 - do NOT depend on anything NOR have any references to Entity objects: violates SRP
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

package use_cases.collaborative_task_scheduling.scheduling_ct_use_case;


import entities.Task;

import java.util.HashMap;

/**
 * Request Model for the Scheduling Collaborative Tasks Use Case
 * Acts as the input data object in the use case layer
 */

public class ScheduleCTRequestModel {

    private final String taskName;

    private final String startTime;

    private final String endTime;

    private final Object studentUser;

    public ScheduleCTRequestModel(String taskName, String startTime, String endTime, Object studentUser) {
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.studentUser = studentUser;
    }

    public String getTaskName() { return taskName; }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Object getStudentUser() { return studentUser; }
}
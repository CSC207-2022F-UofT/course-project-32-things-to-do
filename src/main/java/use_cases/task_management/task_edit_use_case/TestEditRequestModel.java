package use_cases.task_management.task_edit_use_case;

import entities.Test;

import java.time.LocalDateTime;

public class TestEditRequestModel extends TaskEditRequestModel {
    private final String id;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final double weightage;
    private final double timeNeeded;
    private final double timeSpent;
    public TestEditRequestModel(String id, String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                double weightage, double timeNeeded, double timeSpent) {
        super(title, priority);
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
        this.timeNeeded = timeNeeded;
        this.timeSpent = timeSpent;
    }

    // getters
    public String getId() {
        return this.id;
    }
    public LocalDateTime getStartTime() {
        return this.startTime;
    }
    public LocalDateTime getEndTime() {
        return this.endTime;
    }
    public double getWeightage() {
        return this.weightage;
    }
    public double getTimeNeeded() {
        return this.timeNeeded;
    }
    public double getTimeSpent() {
        return this.timeSpent;
    }
}

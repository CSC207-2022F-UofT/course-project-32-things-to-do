package use_cases.task_edit_use_case;

import entities.Test;

import java.time.LocalDateTime;

public class TestEditRequestModel extends TaskEditRequestModel {
    private final Test test;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final double weightage;

    public TestEditRequestModel(Test test, String title, int priority, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        super(title, priority);
        this.test = test;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
    }

    // getters
    public Test getTest() {
        return this.test;
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
}

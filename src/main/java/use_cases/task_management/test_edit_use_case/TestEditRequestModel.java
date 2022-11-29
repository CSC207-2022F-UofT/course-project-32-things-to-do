package use_cases.task_management.test_edit_use_case;

import entities.Test;

import java.time.LocalDateTime;

public class TestEditRequestModel {
    private Test test;
    private String title;
    private int priority;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double weightage;

    public TestEditRequestModel(Test test, String title, int priority, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        this.test = test;
        this.title = title;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
    }

    public String getTitle() {
        return this.title;
    }
    public Test getTest() {
        return this.test;
    }
    public int getPriority() {
        return this.priority;
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

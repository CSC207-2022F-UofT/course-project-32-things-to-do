package test_creation_use_case;

import java.time.LocalDateTime;

public class TestCreationRequestModel {
    private String title;
    private int priority;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double weightage;

    public TestCreationRequestModel(String title, int priority, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        this.title = title;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
    }

    public String getTitle() {
        return this.title;
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

package use_cases.task_management.task_creation_use_case;

import java.time.LocalDateTime;

public class TestCreationRequestModel extends TaskCreationRequestModel {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double weightage;

    public TestCreationRequestModel(String title, int priority, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        super(title, priority);
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
    }

    // getters
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

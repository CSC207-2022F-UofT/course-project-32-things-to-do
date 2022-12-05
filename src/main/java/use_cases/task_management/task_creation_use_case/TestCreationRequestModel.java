package use_cases.task_management.task_creation_use_case;

import java.time.LocalDateTime;

public class TestCreationRequestModel extends TaskCreationRequestModel {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final double weightage;

    /**
     * A request model for Test creation
     * @param title - title of Test
     * @param priority - priority of Test
     * @param startTime - Test's start time
     * @param endTime - Test's end time
     * @param weightage - Test's weightage
     */
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

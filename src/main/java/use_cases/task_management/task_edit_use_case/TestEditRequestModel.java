package use_cases.task_management.task_edit_use_case;

import java.time.LocalDateTime;

public class TestEditRequestModel extends TaskEditRequestModel {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final double weightage;
    private final double timeNeeded;
    private final double timeSpent;

    /**
     * A request model for Test editing
     * @param id - the ID of the Test
     * @param complete - whether the Test is complete
     * @param priority - the priority of the Test
     * @param startTime - the Test's start time
     * @param endTime - the Test's end time
     * @param weightage - the Test's weightage
     * @param timeNeeded - the time needed to study
     * @param timeSpent - the time spent studying
     */
    public TestEditRequestModel(String id, boolean complete, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                double weightage, double timeNeeded, double timeSpent) {
        super(id, complete, priority);
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
        this.timeNeeded = timeNeeded;
        this.timeSpent = timeSpent;
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
    public double getTimeNeeded() {
        return this.timeNeeded;
    }
    public double getTimeSpent() {
        return this.timeSpent;
    }
}

package use_cases.task_management.task_edit_use_case;

import java.time.LocalDateTime;

public class AssignmentEditRequestModel extends TaskEditRequestModel {
    private LocalDateTime dueDate;
    private double weightage;
    private double timeNeeded;
    private double timeSpent;

    public AssignmentEditRequestModel(String id, boolean complete, int priority, LocalDateTime dueDate, double weightage,
                                      double timeNeeded, double timeSpent) {
        super(id, complete, priority);
        this.dueDate = dueDate;
        this.weightage = weightage;
        this.timeNeeded = timeNeeded;
        this.timeSpent = timeSpent;
    }
    public LocalDateTime getDueDate() {
        return this.dueDate;
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

package use_cases.task_management.task_edit_use_case;

import entities.Assignment;

import java.time.LocalDateTime;

public class AssignmentEditRequestModel extends TaskEditRequestModel {
    String id;
    private LocalDateTime dueDate;
    private double weightage;
    private double timeNeeded;
    private double timeSpent;

    public AssignmentEditRequestModel(String id, String title, int priority, LocalDateTime dueDate, double weightage,
                                      double timeNeeded, double timeSpent) {
        super(title, priority);
        this.id = id;
        this.dueDate = dueDate;
        this.weightage = weightage;
        this.timeNeeded = timeNeeded;
        this.timeSpent = timeSpent;
    }
    public String getId() {
        return this.id;
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

package use_cases.task_management.task_edit_use_case;

import entities.Assignment;

import java.time.LocalDateTime;

public class AssignmentEditRequestModel extends TaskEditRequestModel {
    Assignment assignment;
    private LocalDateTime dueDate;
    private double weightage;

    public AssignmentEditRequestModel(Assignment assignment, String title, int priority, LocalDateTime dueDate, double weightage) {
        super(title, priority);
        this.assignment = assignment;
        this.dueDate = dueDate;
        this.weightage = weightage;
    }
    public Assignment getAssignment() {
        return this.assignment;
    }
    public LocalDateTime getDueDate() {
        return this.dueDate;
    }
    public double getWeightage() {
        return this.weightage;
    }
}

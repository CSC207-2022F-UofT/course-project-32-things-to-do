package assignment_edit_use_case;

import entities.Assignment;

import java.time.LocalDateTime;

public class AssignmentEditRequestModel {
    Assignment assignment;
    private String title;
    private int priority;
    private LocalDateTime dueDate;
    private double weightage;

    public AssignmentEditRequestModel(Assignment assignment, String title, int priority, LocalDateTime dueDate, double weightage) {
        this.assignment = assignment;
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.weightage = weightage;
    }
    public Assignment getAssignment() {
        return this.assignment;
    }
    public String getTitle() {
        return this.title;
    }
    public int getPriority() {
        return this.priority;
    }
    public LocalDateTime getDueDate() {
        return this.dueDate;
    }
    public double getWeightage() {
        return this.weightage;
    }
}

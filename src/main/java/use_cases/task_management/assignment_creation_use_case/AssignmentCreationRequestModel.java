package use_cases.task_management.assignment_creation_use_case;

import java.time.LocalDateTime;

public class AssignmentCreationRequestModel {
    private String title;
    private int priority;
    private LocalDateTime dueDate;
    private double weightage;

    public AssignmentCreationRequestModel(String title, int priority, LocalDateTime dueDate, double weightage) {
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.weightage = weightage;
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

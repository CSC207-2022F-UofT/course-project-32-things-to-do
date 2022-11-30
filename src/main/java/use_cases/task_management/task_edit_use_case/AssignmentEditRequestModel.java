package use_cases.task_management.task_edit_use_case;

import entities.Assignment;

import java.time.LocalDateTime;

public class AssignmentEditRequestModel extends TaskEditRequestModel {
    String id;
    private LocalDateTime dueDate;
    private double weightage;

    public AssignmentEditRequestModel(String id, String title, int priority, LocalDateTime dueDate, double weightage) {
        super(title, priority);
        this.id = id;
        this.dueDate = dueDate;
        this.weightage = weightage;
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
}

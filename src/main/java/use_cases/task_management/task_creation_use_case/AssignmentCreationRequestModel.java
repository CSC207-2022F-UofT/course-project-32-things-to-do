package use_cases.task_management.task_creation_use_case;

import java.time.LocalDateTime;

public class AssignmentCreationRequestModel extends TaskCreationRequestModel {
    private LocalDateTime dueDate;
    private double weightage;

    public AssignmentCreationRequestModel(String title, int priority, LocalDateTime dueDate, double weightage) {
        super(title, priority);
        this.dueDate = dueDate;
        this.weightage = weightage;
    }
    // getters
    public LocalDateTime getDueDate() {
        return this.dueDate;
    }
    public double getWeightage() {
        return this.weightage;
    }
}

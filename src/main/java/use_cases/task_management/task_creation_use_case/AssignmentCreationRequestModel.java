package use_cases.task_management.task_creation_use_case;

import java.time.LocalDateTime;

public class AssignmentCreationRequestModel extends TaskCreationRequestModel {
    private final LocalDateTime dueDate;
    private final double weightage;

    /**
     * A request model for Assignment creation
     * @param title - the title of the Assignment
     * @param priority - the priority of the Assignment
     * @param dueDate - the Assignment's due date
     * @param weightage - the Assignment's weightage
     */
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

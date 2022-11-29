<<<<<<<< HEAD:src/main/java/use_cases/task_management/assignment_creation_use_case/AssignmentCreationRequestModel.java
package use_cases.task_management.assignment_creation_use_case;
========
package task_creation_use_case;
>>>>>>>> e506501 (Condensed all Task creation and editing use cases (previously 6 total) into 2: one for each use case. Also made slight changes to Task to attempt to fix a bug, to no avail):src/main/java/task_creation_use_case/AssignmentCreationRequestModel.java

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

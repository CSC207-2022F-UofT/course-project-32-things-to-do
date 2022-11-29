<<<<<<<< HEAD:src/main/java/use_cases/task_management/assignment_edit_use_case/AssignmentEditRequestModel.java
package use_cases.task_management.assignment_edit_use_case;
========
package task_edit_use_case;
>>>>>>>> e506501 (Condensed all Task creation and editing use cases (previously 6 total) into 2: one for each use case. Also made slight changes to Task to attempt to fix a bug, to no avail):src/main/java/task_edit_use_case/AssignmentEditRequestModel.java

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

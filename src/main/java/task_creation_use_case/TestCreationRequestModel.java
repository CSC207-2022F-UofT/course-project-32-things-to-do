<<<<<<<< HEAD:src/main/java/use_cases/task_management/test_creation_use_case/TestCreationRequestModel.java
package use_cases.task_management.test_creation_use_case;
========
package task_creation_use_case;
>>>>>>>> e506501 (Condensed all Task creation and editing use cases (previously 6 total) into 2: one for each use case. Also made slight changes to Task to attempt to fix a bug, to no avail):src/main/java/task_creation_use_case/TestCreationRequestModel.java

import java.time.LocalDateTime;

public class TestCreationRequestModel extends TaskCreationRequestModel {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double weightage;

    public TestCreationRequestModel(String title, int priority, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        super(title, priority);
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
    }

    // getters
    public LocalDateTime getStartTime() {
        return this.startTime;
    }
    public LocalDateTime getEndTime() {
        return this.endTime;
    }
    public double getWeightage() {
        return this.weightage;
    }
}

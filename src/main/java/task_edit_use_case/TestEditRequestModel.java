<<<<<<<< HEAD:src/main/java/use_cases/task_management/test_edit_use_case/TestEditRequestModel.java
package use_cases.task_management.test_edit_use_case;
========
package task_edit_use_case;
>>>>>>>> e506501 (Condensed all Task creation and editing use cases (previously 6 total) into 2: one for each use case. Also made slight changes to Task to attempt to fix a bug, to no avail):src/main/java/task_edit_use_case/TestEditRequestModel.java

import entities.Test;

import java.time.LocalDateTime;

public class TestEditRequestModel extends TaskEditRequestModel {
    private final Test test;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final double weightage;

    public TestEditRequestModel(Test test, String title, int priority, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        super(title, priority);
        this.test = test;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
    }

    // getters
    public Test getTest() {
        return this.test;
    }
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

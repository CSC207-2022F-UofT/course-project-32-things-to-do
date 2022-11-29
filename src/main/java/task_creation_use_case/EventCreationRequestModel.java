<<<<<<<< HEAD:src/main/java/use_cases/task_management/event_creation_use_case/EventCreationRequestModel.java
package use_cases.task_management.event_creation_use_case;
========
package task_creation_use_case;
>>>>>>>> e506501 (Condensed all Task creation and editing use cases (previously 6 total) into 2: one for each use case. Also made slight changes to Task to attempt to fix a bug, to no avail):src/main/java/task_creation_use_case/EventCreationRequestModel.java

import java.time.LocalDateTime;

public class EventCreationRequestModel extends TaskCreationRequestModel {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean recurring;
    private String frequency;

    public EventCreationRequestModel(String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                     boolean recurring, String frequency) {
        super(title, priority);
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurring = recurring;
        this.frequency = frequency;
    }
    public LocalDateTime getStartTime() {
        return this.startTime;
    }
    public LocalDateTime getEndTime() {
        return this.endTime;
    }
    public boolean getRecurring() {
        return this.recurring;
    }
    public String getFrequency() {
        return this.frequency;
    }
}

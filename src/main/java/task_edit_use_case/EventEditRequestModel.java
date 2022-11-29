<<<<<<<< HEAD:src/main/java/use_cases/task_management/event_edit_use_case/EventEditRequestModel.java
package use_cases.task_management.event_edit_use_case;
========
package task_edit_use_case;
>>>>>>>> e506501 (Condensed all Task creation and editing use cases (previously 6 total) into 2: one for each use case. Also made slight changes to Task to attempt to fix a bug, to no avail):src/main/java/task_edit_use_case/EventEditRequestModel.java

import entities.Event;

import java.time.LocalDateTime;

public class EventEditRequestModel extends TaskEditRequestModel {
    private Event event;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean recurring;
    private String frequency;

    public EventEditRequestModel(Event event, String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                 boolean recurring, String frequency) {
        super(title, priority);
        this.event = event;
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurring = recurring;
        this.frequency = frequency;
    }
    public Event getEvent() {
        return this.event;
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

package use_cases.task_edit_use_case;

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

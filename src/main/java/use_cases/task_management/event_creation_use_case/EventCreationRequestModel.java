package use_cases.task_management.event_creation_use_case;

import java.time.LocalDateTime;

public class EventCreationRequestModel {
    private String title;
    private int priority;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean recurring;
    private String frequency;

    public EventCreationRequestModel(String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                     boolean recurring, String frequency) {
        this.title = title;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurring = recurring;
        this.frequency = frequency;
    }
    public String getTitle() {
        return this.title;
    }
    public int getPriority() {
        return this.priority;
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

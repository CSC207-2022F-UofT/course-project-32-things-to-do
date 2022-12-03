package use_cases.task_management.task_creation_use_case;

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

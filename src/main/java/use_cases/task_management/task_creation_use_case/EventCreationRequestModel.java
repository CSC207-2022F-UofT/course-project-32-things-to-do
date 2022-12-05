package use_cases.task_management.task_creation_use_case;

import java.time.LocalDateTime;

public class EventCreationRequestModel extends TaskCreationRequestModel {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final boolean recurring;
    private final String frequency;

    /**
     * A request model for Event creation
     * @param title - the title of the Event
     * @param priority - the priority of the Event
     * @param startTime - the Event's start time
     * @param endTime - the Event's end time
     * @param recurring - whether the event is recurring
     * @param frequency - the Event's frequency (if recurring)
     */
    public EventCreationRequestModel(String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                     boolean recurring, String frequency) {
        super(title, priority);
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurring = recurring;
        this.frequency = frequency;
    }

    // getters
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

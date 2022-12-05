package use_cases.task_management.task_edit_use_case;

import java.time.LocalDateTime;

public class EventEditRequestModel extends TaskEditRequestModel {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final boolean recurring;
    private final String frequency;

    /**
     * A request model for editing Events
     * @param id - the ID of the Event
     * @param complete - whether the Event is complete
     * @param priority - priority of the Event
     * @param startTime - start time of Event
     * @param endTime - end time of Event
     * @param recurring - whether the Event is recurring
     * @param frequency - frequency of Event
     */
    public EventEditRequestModel(String id, boolean complete, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                 boolean recurring, String frequency) {
        super(id, complete, priority);
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

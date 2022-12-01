package use_cases.task_management.task_edit_use_case;

import java.time.LocalDateTime;

public class EventEditRequestModel extends TaskEditRequestModel {
    private boolean complete;
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean recurring;
    private String frequency;

    public EventEditRequestModel(String id, boolean complete, String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                 boolean recurring, String frequency) {
        super(id, complete, title, priority);
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurring = recurring;
        this.frequency = frequency;
    }
    public String getId() {
        return this.id;
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

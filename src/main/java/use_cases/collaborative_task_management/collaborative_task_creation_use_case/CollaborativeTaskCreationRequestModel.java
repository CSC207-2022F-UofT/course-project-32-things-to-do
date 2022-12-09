package use_cases.collaborative_task_management.collaborative_task_creation_use_case;

import use_cases.task_management.task_creation_use_case.TaskCreationRequestModel;

import java.time.LocalDateTime;

/**
 * Request Model for the Collaborative Task Creation Use Case
 * Acts as the input data object in the use case layer
 */

public class CollaborativeTaskCreationRequestModel extends TaskCreationRequestModel {
    private final boolean recurring;
    private final String frequency;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final LocalDateTime deadline;

    /**
     * A request model for Collaborative Task creation
     * @param title - the title of the Collaborative task
     * @param priority - the priority of the Collaborative Task
     * @param recurring - whether the Collaborative Task is recurring
     * @param frequency - the Collaborative Task's frequency (if recurring)
     * @param startTime - the Collaborative Task's start time
     * @param endTime - the Collaborative Task's end time
     * @param deadline - the Collaborative Task's deadline
     */
    public CollaborativeTaskCreationRequestModel(String title, int priority, boolean recurring, String frequency, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime deadline) {
        super(title, priority);
        this.recurring = recurring;
        this.frequency = frequency;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deadline = deadline;
    }

    public boolean getRecurring() {
        return this.recurring;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }
}
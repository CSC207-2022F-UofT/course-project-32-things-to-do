package collaborative_task_creation_use_case;

import entities.CollaborativeTask;
import entities.StudentUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CollaborativeTaskCreationRequestModel {
    private final String title;
    private final int priority;
    private final boolean recurring;
    private final String frequency;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final LocalDateTime deadline;
    private final StudentUser leader;

    public CollaborativeTaskCreationRequestModel(String title, int priority, boolean recurring, String frequency, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime deadline, StudentUser leader) {
        this.title = title;
        this.priority = priority;
        this.recurring = recurring;
        this.frequency = frequency;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deadline = deadline;
        this.leader = leader;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPriority() {
        return this.priority;
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

    public StudentUser getLeader() {
        return this.leader;
    }
}
package collaborative_task_edit_use_case;

import entities.CollaborativeTask;
import entities.StudentUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CollaborativeTaskEditRequestModel {
    private final CollaborativeTask collaborativeTask;
    private final String title;
    private final int priority;
    private final boolean recurring;
    private final String frequency;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final LocalDateTime deadline;
    private final ArrayList<StudentUser> teammates;
    private final StudentUser leader;


    public CollaborativeTaskEditRequestModel(CollaborativeTask collaborativeTask, String title, int priority, boolean recurring, String frequency, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime deadline, ArrayList<StudentUser> teammates, StudentUser leader) {
        this.collaborativeTask = collaborativeTask;
        this.title = title;
        this.priority = priority;
        this.recurring = recurring;
        this.frequency = frequency;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deadline = deadline;
        this.teammates = teammates;
        this.leader = leader;
    }
    public CollaborativeTask getCollaborativeTask() {
        return this.collaborativeTask;
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

    public ArrayList<StudentUser> getTeammates() {
        return this.teammates;
    }

    public StudentUser getLeader() {
        return this.leader;
    }
}
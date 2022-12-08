package use_cases.collaborative_task_management.collaborative_task_edit_use_case;

import entities.StudentUser;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CollaborativeTaskEditRequestModel {
    private final String id;
    private final boolean complete;
    private final int priority;
    private final boolean recurring;
    private final String frequency;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final LocalDateTime deadline;
    private final ArrayList<StudentUser> teammates;
    private final StudentUser leader;

    /**
     * A request model for editing Collaborative Tasks.
     * @param id - the ID of the Collaborative Task
     * @param complete - whether the Collaborative task is complete
     * @param priority - the priority of the Collaborative Task
     * @param recurring - whether the Collaborative Task is recurring
     * @param frequency - the frequency of the Collaborative Task if recurring
     * @param startTime - the start time of the Collaborative Task
     * @param endTime - the end time of the Collaborative Task
     * @param deadline - the Collaborative Task's deadline
     * @param teammates - the Collaborative Task's teammates
     * @param leader - the Collaborative Task's leader
     */
    public CollaborativeTaskEditRequestModel(String id, boolean complete, int priority, boolean recurring, String frequency, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime deadline, ArrayList<StudentUser> teammates, StudentUser leader) {
        this.id = id;
        this.complete = complete;
        this.priority = priority;
        this.recurring = recurring;
        this.frequency = frequency;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deadline = deadline;
        this.teammates = teammates;
        this.leader = leader;
    }

    public String getId() {
        return this.id;
    }

    public boolean isComplete() { return this.complete; }

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
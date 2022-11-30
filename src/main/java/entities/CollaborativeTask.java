package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CollaborativeTask extends Task implements Timeblockable {
    private boolean recurring;
    private String frequency;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime deadline;
    private ArrayList<ArrayList<LocalDateTime>> timeBlocks;
    private ArrayList<StudentUser> teammates;
    private ArrayList<StudentUser> pendingTeammates;
    private ArrayList<StudentUser> declinedTeammates;
    private StudentUser leader;

    /**
     * Create a CollaborativeTask with a title, priority, and deadline
     * If the collaborative task is recurring, indicate the frequency (eg "daily", "weekly", "monthly)
     * Otherwise, the frequency is blank
     *
     * @param title     - the title of the Collaborative Task
     * @param id        - the unique ID of the Collaborative Task
     * @param priority  - the priority value of the Collaborative Task
     * @param recurring - whether the Collaborative Task is recurring
     * @param frequency - the frequency at which the Collaborative Task occurs (if recurring)
     * @param startTime = the start time and date of the first occurrence of the Collaborative Task
     * @param endTime   = the end time and date of the first occurrence of the Collaborative Task
     * @param deadline  - the time at which the Collaborative Task is due
     * @param creator   - the Student User who creates the Collaborative Task
     */
    public CollaborativeTask(String title, String id, int priority, boolean recurring, String frequency, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime deadline, StudentUser creator) {
        super(title, id, priority);
        this.recurring = recurring;
        if (recurring) {
            this.frequency = frequency;
        } else {
            this.frequency = "";
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.deadline = deadline;
        this.leader = creator;
    }

    /**
     * @return whether the Collaborative Task is reoccurring or not.
     */
    public boolean getRecurring() {
        return this.recurring;
    }

    /**
     * @return frequency of Collaborative Task.
     */
    public String getFrequency() {
        return this.frequency;
    }

    /**
     * Set a Collaborative Task as recurring/not
     * Set its new frequency if it is recurring
     *
     * @param recurring - whether the Collaborative Task is recurring
     * @param frequency - the frequency at which the Collaborative Task occurs (if recurring)
     */
    public void setRecurringAndFrequency(boolean recurring, String frequency) {
        this.recurring = recurring;
        if (recurring) {
            this.frequency = frequency;
        } else {
            this.frequency = "";
        }
    }

    /**
     * @return start time for the initial occurrence of the Collaborative Task.
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Set a start time for Collaborative Task.
     *
     * @param startTime - start time for the initial occurrence of the Collaborative Task.
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end time for the initial occurrence of the Collaborative Task.
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Set a end time for Collaborative Task.
     *
     * @param endTime - end time for the initial occurrence of the Collaborative Task.
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @return deadline for Collaborative Task.
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Set a deadline for a Collaborative Task.
     *
     * @param deadline - deadline for Collaborative Task.
     */
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    // The following four methods (getTimeBlock, setTimeBlock, scheduleTimeBlock, and removeTimeBlock) are here because this class implements Timeblockable/
    // Based on the way Collaborative Tasks are scheduled (Feature 5), not sure if they remain necessary.

    /**
     * Set the time block of a Collaborative Task
     *
     * @return - the time block of the Collaborative Task
     * - in array form: {startTime, endTime}
     */
    public LocalDateTime[] getTimeBlock() {
        return new LocalDateTime[]{this.startTime, this.endTime};
    }

    /**
     * Set a new time block and then schedule it
     *
     * @param startTime - the start of the time block
     * @param endTime   - the end of the time block
     */
    public void setTimeBlock(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        scheduleTimeBlock();
    }

    /**
     * Schedule a time block for the user
     *
     * @return - whether the time block has been successfully scheduled
     */
    public boolean scheduleTimeBlock() {
        return true;
    }

    /**
     * Remove a time block from the user's schedule
     *
     * @return - whether the time block has been successfully removed
     */
    public boolean removeTimeBlock() {
        return true;
    }

    /**
     * Get the time blocks of a Collaborative Task.
     *
     * @return - the time blocks of the Collaborative task in an Array List of Array Losts of LocalDateTimes.
     */
    public ArrayList<ArrayList<LocalDateTime>> getTimeBlocks() {
        return this.timeBlocks;
    }

    /**
     * Set new time blocks
     *
     * @param timeBlocks - Array List of Array Lists of time blocks of the Collaborative Task.
     */
    public void setTimeBlocks(ArrayList<ArrayList<LocalDateTime>> timeBlocks) {
        this.timeBlocks = timeBlocks;
    }

    /**
     * @return leader of the Collaborative Task
     */
    public StudentUser getLeader() {
        return this.leader;
    }

    /**
     * Set a new leader of the Collaborative Task.
     * This method should only be used by the current leader of the Collaborative Task.
     *
     * @param leader - the Student User who is going to replace the current leader of the Collaborative Task.
     */
    public void setLeader(StudentUser leader) {
        this.leader = leader;
    }

    /**
     * @return an Array List of all Student Users who are teammates on this Collaborative Task, including the leader.
     */
    public ArrayList<StudentUser> getTeammates() {
        return this.teammates;
    }

    /**
     * Set a new Array List of Student User teammates.
     * To be used when invitations are accepted, teammates are removed by leader, or teammates remove themselves.
     *
     * @param teammates - an Array List of Student Users who are to be added as teammates.
     */
    public void setTeammates(ArrayList<StudentUser> teammates) {
        this.teammates = teammates;
    }

    /**
     * @return an Array List of all Student Users who have received invitations to join
     * the Collaborative Task but not responded.
     */
    public ArrayList<StudentUser> getPendingTeammates() {
        return this.pendingTeammates;
    }

    /**
     * Set a new Array List of Student User pending teammates.
     * To be used when invitations to join a Collaborative Task are sent, canceled, or responded to.
     *
     * @param pendingTeammates - an Array List of Student Users who are being invited to join the Collaborative Task.
     */
    public void setPendingTeammates(ArrayList<StudentUser> pendingTeammates) {
        this.pendingTeammates = pendingTeammates;
    }


    /**
     * @return an Array List of all Student Users who have declined invitations to join the Collaborative Task.
     */
    public ArrayList<StudentUser> getDeclinedTeammates() {
        return this.declinedTeammates;
    }

    /**
     * Set a new Array List of Student User declined teammates.
     * To be used when invitations to join a Collaborative Task are declined or when a Student User who has previously
     * declined an invitation to join the Collaborative Task has accepted a reinvitation to join.
     *
     * @param declinedTeammates - an Array List of Student Users who have declined invitations to join the Collaborative Task.
     */
    public void setDeclinedTeammates(ArrayList<StudentUser> declinedTeammates) {
        this.declinedTeammates = declinedTeammates;
    }


}


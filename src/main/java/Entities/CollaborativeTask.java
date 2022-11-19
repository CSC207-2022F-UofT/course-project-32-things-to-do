package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CollaborativeTask extends Task implements Timeblockable{
    private boolean recurring;
    private String frequency;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ArrayList<StudentUser> teammates;
    private ArrayList<StudentUser> pendingTeammates;
    private ArrayList<StudentUser> declinedTeammates;
    private StudentUser leader;


    /**
     * Create a CollaborativeTask with a title
     * If the collaborative task is recurring, indicate the frequency (eg "daily", "weekly", "monthly)
     * Otherwise, the frequency is blank
     * @param title - the title of the Collaborative Task
     * @param id - the unique ID of the Collaborative Task
     * @param recurring - whether the Collaborative Task is recurring
     * @param frequency - the frequency at which the Collaborative Task occurs (if recurring)
     * @param creator - the Student User who creates the Collaborative Task
     */
    public CollaborativeTask(String title, String id, boolean recurring, String frequency, StudentUser creator) {
        super(title, id);
        this.recurring = recurring;
        if (recurring) this.frequency = frequency;
        else this.frequency = "";
        this.leader = creator;
    }

    /**
     * Create a Collaborative Task with a title and priority
     * If the collaborative task is recurring, indicate the frequency (eg "daily", "weekly", "monthly)
     * Otherwise, the frequency is blank
     * @param title - the title of the Collaborative Task
     * @param id - the unique ID of the Collaborative Task
     * @param priority - the priority value of the Collaborative Task
     * @param recurring - whether the Collaborative Task is recurring
     * @param frequency - the frequency at which the Collaborative Task occurs (if recurring)
     * @param creator - the Student User who creates the Collaborative Task
     */
    public CollaborativeTask(String title, String id, int priority, boolean recurring, String frequency, StudentUser creator) {
        super(title, id, priority);
        this.recurring = recurring;
        this.frequency = frequency;
        this.leader = creator;
    }

    /**
     * @return leader of the Collaborative Task
     */
    protected StudentUser getLeader(){
        return this.leader;
    }

    /**
     * Set a new leader of the Collaborative Task.
     * This method should only be used by the current leader of the Collaborative Task.
     * @param leader - the Student User who is going to replace the current leader of the Collaborative Task.
     */
    protected void setLeader(StudentUser leader){
        this.leader = leader;
    }

    /**
     * @return an Array List of all Student Users who are teammates on this Collaborative Task, including the leader.
     */
    protected ArrayList<StudentUser> getTeammates(){
        return this.teammates;
    }

    /**
     * To be used when invitations to join a Collaborative Task are accepted.
     * @param newTeammates - an Array List of Student Users who are to be added as teammates.
     */
    protected void addTeammates(ArrayList<StudentUser> newTeammates){
        this.teammates.addAll(newTeammates);
    }

    /**
     * When this method is used by the leader of the Collaborative Task,
     * the oldTeammates parameter can contain any Student User in the Collaborative Task's teammates list.
     * When this method is used by any other teammate of the Collaborative Task,
     * the oldTeammates parameter can only contain themselves.
     * @param oldTeammates - an Array List of Student Users who are to be removed as teammates.
     */
    protected void removeTeammates(ArrayList<StudentUser> oldTeammates){
        this.teammates.removeAll(oldTeammates);
    }

    /**
     * @return an Array List of all Student Users who have received invitations to join
     * the Collaborative Task but not responded.
     */
    protected ArrayList<StudentUser> getPendingTeammates(){
        return this.pendingTeammates;
    }

    /**
     * To be used when invitations to join a Collaborative Task are sent.
     * @param newTeammates - an Array List of Student Users who are being invited to join the Collaborative Task.
     */
    protected void addPendingTeammates(ArrayList<StudentUser> newTeammates){
        this.pendingTeammates.addAll(newTeammates);
    }

    /**
     * To be used when invitations to join a Collaborative Task are canceled or responded to.
     * @param oldTeammates - an Array List of Student Users who are no longer undergoing the process of being
     *                       invited to join the Collaborative Task.
     */
    protected void removePendingTeammates(ArrayList<StudentUser> oldTeammates){
        this.pendingTeammates.removeAll(oldTeammates);
    }

    /**
     * @return an Array List of all Student Users who have declined invitations to join the Collaborative Task.
     */
    protected ArrayList<StudentUser> getDeclinedTeammates(){
        return this.declinedTeammates;
    }

    /**
     * To be used when invitations to join a Collaborative Task are declined.
     * @param newTeammates - an Array List of Student Users who have declined invitations to join the Collaborative Task.
     */
    protected void addDeclinedTeammates(ArrayList<StudentUser> newTeammates){
        this.declinedTeammates.addAll(newTeammates);
    }

    /**
     * To be used when a Student User who has previously declined an invitation to join the Collaborative Task has
     * accepted a reinvitation to join.
     * @param oldTeammates - an Array List of Student Users who have accepted an invitation to join the Collaborative
     *                       Task after previously declining such an invitation.
     */
    protected void removeDeclinedTeammates(ArrayList<StudentUser> oldTeammates){
        this.declinedTeammates.removeAll(oldTeammates);
    }

    /**
     * Set a Collaborative Task as recurring/not
     * Set its new frequency if it is recurring
     * @param recurring - whether the Collaborative Task is recurring
     * @param frequency - the frequency at which the Collaborative Task occurs (if recurring)
     */
    protected void setRecurring(boolean recurring, String frequency) {
        this.recurring = recurring;
        if (recurring) {
            this.frequency = frequency;
        }
        else{
            this.frequency = "";
        }
    }

    /**
     * Set a new time block
     * @param startTime - the start of the time block
     * @param endTime - the end of the time block
     */
    public void setTimeBlock(LocalDateTime startTime, LocalDateTime endTime) {

    }

    /**
     * Get the time block of an Event
     * @return - the time block of the Event
     *         - in array form: {startTime, endTime}
     */
    public LocalDateTime[] getTimeBlock() {
        return new LocalDateTime[] {this.startTime, this.endTime};
    }

    /**
     * Schedule a time block for the user
     * @return - whether the time block has been successfully scheduled
     */
    public boolean scheduleTimeBlock() {
        return true;
    }

    /**
     * Remove a time block from the user's schedule
     * @return - whether the time block has been successfully removed
     */
    public boolean removeTimeBlock() {
        return true;
    }

    /**
     * Delete a Collaborative Task by moving it to the user's archive
     * @return - whether the Collaborative Task has been successfully deleted
     */
    protected boolean delete() {
        return true;
    }

    /**
     * Save a Collaborative Task to the user's data
     * @return - whether the Collaborative Task has been successfully saved
     */
    protected boolean save() {
        return true;
    }

    /**
     * Edit the features of the Collaborative Task.
     */
    protected void edit(){

    }
}

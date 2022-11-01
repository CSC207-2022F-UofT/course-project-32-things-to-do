package entities;

import java.util.ArrayList;

public class CollaborativeTask extends Task implements Timeblockable{
    private boolean recurring;
    private String frequency;
    private ArrayList<StudentUser> teammates;
    private ArrayList<StudentUser> pendingTeammates;
    private ArrayList<StudentUser> declinedTeammates;
    private StudentUser leader;

    /**
     * Create a CollaborativeTask with a title
     * If the collaborative task is recurring, indicate the frequency (eg "daily", "weekly", "monthly)
     * Otherwise, the frequency is blank
     * @param title - the title of the Collaborative Task
     * @param recurring - whether the Collaborative Task is recurring
     * @param frequency - the frequency at which the Collaborative Task occurs (if recurring)
     */
    public CollaborativeTask(String title, boolean recurring, String frequency, StudentUser creator) {
        super(title);
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
     * @param priority - the priority value of the Collaborative Task
     * @param recurring - whether the Collaborative Task is recurring
     * @param frequency - the frequency at which the Collaborative Task occurs (if recurring)
     */
    public CollaborativeTask(String title, int priority, boolean recurring, String frequency) {
        super(title, priority);
        this.recurring = recurring;
        this.frequency = frequency;
    }

    /**
     * Set a Collaborative Task as recurring/not
     * Set its new frequency if it is recurring
     * @param recurring - whether the Collaborative Task is recurring
     * @param frequency - the frequency at which the Collaborative Task occurs (if recurring)
     */
    protected void setRecurring(boolean recurring, String frequency) {
        if (recurring) this.frequency = frequency;
    }

    /**
     * Set a new time block
     * @param newTimeBlock - the new time block of the Collaborative Task
     */
    public void setTimeBlock(int[] newTimeBlock) {

    }

    /**
     * Get the time block of an Collaborative Task
     * @return - the time block of the Collaborative Task
     */
    public int[] getTimeBlock() {
        return this.timeBlock;
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
     * Edit the features of the Collaborative Task
     */
    protected void edit() {

    }
}

package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Test extends Task implements Timeblockable, Gradable, Preparatory {
    // Timeblockable attributes
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Gradable attributes
    private double weightage = 0;
    private double gradeReceived = -1; // the grade the user receives, -1 if not yet received

    // Preparatory attributes
    private double timeSpent = 0;
    private double timeNeeded = 0;
    private ArrayList<ArrayList<LocalDateTime>> prepTimeScheduled;

    /**
     * Create a new Test with a title and time block
     * @param title - name of the Test
     * @param id - the unique ID of the Test
     * @param startTime - start of time block
     * @param endTime - end of time block
     */
    public Test(String title, String id, LocalDateTime startTime, LocalDateTime endTime) {
        super(title, id);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Create a new Test with a title, priority value, and time block
     * @param title - name of the test
     * @param id - the unique ID of the Test
     * @param priority - priority value of the test
     * @param startTime - start of time block
     * @param endTime - end of time block
     */
    public Test(String title, String id, int priority, LocalDateTime startTime, LocalDateTime endTime) {
        super(title, id, priority);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    /**
     * Set a new time block
     * @param startTime - the start of the new time block
     * @param endTime - the end of the new time block
     */
    public void setTimeBlock(LocalDateTime startTime, LocalDateTime endTime) {

    }

    /**
     * Get the time block of a Test
     * @return - the time block of the Test
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
     * Change the weightage of the Test
     * @param weightage - the new weightage
     */
    public void setWeightage(double weightage) {

    }

    /**
     * Update the Test with the user's grade
     * @param grade - the grade the user has received
     */
    public void setGradeReceived(double grade) {

    }
    /**
     * Update the amount of time the user has spent preparing
     * @param timeSpent - the amount of time being added
     */
    public void updateTimeSpent(double timeSpent) {
        this.timeSpent += timeSpent;
    }

    /**
     * Set the amount of time the user needs to prepare for
     * @param timeNeeded - the new time
     */
    public void setTimeNeeded(double timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    /**
     * Get the amount of time the user has left to prepare (before due date)
     * @return - the amount of time the user has remaining
     */
    public double getTimeLeft() {
        // subtract current date from beginning of time block
        return 0;
    }

    /**
     * Schedule the required prep time for the Test and add to prepTimeScheduled
     * @return - whether scheduling was successful
     */
    public boolean schedulePrepTime() {
        return true;
    }

    /**
     * Get all scheduled prep times
     * @return - all scheduled prep time
     */
    public ArrayList<ArrayList<LocalDateTime>> getPrepTimeScheduled() {
        return prepTimeScheduled;
    }

    /**
     * Delete a Test by moving it to the user's archive
     * @return - whether the Test has been successfully deleted
     */
    protected boolean delete() {
        return true;
    }

    /**
     * Save a Test to the user's data
     * @return - whether the Test has been successfully saved
     */
    protected boolean save() {
        return true;
    }

    /**
     * Edit the features of the Test
     */
    protected void edit() {

    }
}

package Entities;

import java.time.LocalDateTime;

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

    public Test(String title) {
        super(title);
    }
    public Test(String title, int priority) {
        super(title, priority);
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
     * Schedule the required prep time for the Test
     * @return - whether scheduling was successful
     */
    public boolean schedulePrepTime() {
        return true;
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

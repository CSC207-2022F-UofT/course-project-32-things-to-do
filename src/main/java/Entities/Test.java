package Entities;

public class Test extends Task implements Timeblockable, Gradable {
    // Timeblockable attributes
    private int[] timeBlock = new int[2];

    // Gradable attributes
    private double weightage = 0;
    private double gradeReceived = -1; // the grade the user receives, -1 if not yet received

    public Test(String title) {
        super(title);
    }
    public Test(String title, int priority) {
        super(title, priority);
    }
    /**
     * Set a new time block
     * @param newTimeBlock - the new time block of the Entities.Test
     */
    public void setTimeBlock(int[] newTimeBlock) {

    }

    /**
     * Get the time block of a Entities.Test
     * @return - the time block of the Entities.Test
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
     * Change the weightage of the Entities.Test
     * @param weightage - the new weightage
     */
    public void setWeightage(double weightage) {

    }

    /**
     * Update the Entities.Test with the user's grade
     * @param grade - the grade the user has received
     */
    public void setGradeReceived(double grade) {

    }

    /**
     * Set a grade goal for the Entities.Test
     * @param goal - the grade the user would like to receive
     */
    public void setGradeGoal(double goal) {

    }
    /**
     * Delete a Entities.Test by moving it to the user's archive
     * @return - whether the Entities.Test has been successfully deleted
     */
    protected boolean delete() {
        return true;
    }

    /**
     * Save a Entities.Test to the user's data
     * @return - whether the Entities.Test has been successfully saved
     */
    protected boolean save() {
        return true;
    }

    /**
     * Edit the features of the Entities.Test
     */
    protected void edit() {

    }
}

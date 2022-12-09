package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.*;

public class Test extends Task implements Timeblockable, Gradable, Preparatory {
    // Timeblockable attributes
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Gradable attributes
    private double weightage;
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
    public Test(String title, String id, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        super(title, id);
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
    }

    /**
     * Create a new Test with a title, priority value, and time block
     * @param title - name of the test
     * @param id - the unique ID of the Test
     * @param priority - priority value of the test
     * @param startTime - start of time block
     * @param endTime - end of time block
     */
    public Test(String title, String id, int priority, LocalDateTime startTime, LocalDateTime endTime, double weightage) {
        super(title, id, priority);
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
    }
    /**
     * Set a new time block
     * @param startTime - the start of the new time block
     * @param endTime - the end of the new time block
     */
    public void setTimeBlock(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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
     */
    public void scheduleTimeBlock() {
    }

    /**
     * Remove a time block from the user's schedule
     * @return - whether the time block has been successfully removed
     */
    public boolean removeTimeBlock() {
        return true;
    }

    /**
     * Get the weightage of the Test
     * @return - the Test's weightage
     */
    public double getWeightage() {
        return this.weightage;
    }

    /**
     * Change the weightage of the Test
     * @param weightage - the new weightage
     */
    public void setWeightage(double weightage) {
        this.weightage = weightage;
    }

    /**
     * Retrieve the grade that the user received on the Test
     * @return - the user's received grade
     */
    public double getGradeReceived() {
        return this.gradeReceived;
    }

    /**
     * Update the Test with the user's grade
     * @param gradeReceived - the grade the user has received
     */
    public void setGradeReceived(double gradeReceived) {
        this.gradeReceived = gradeReceived;
    }

    /**
     * Get the amount of time spent on the Test so far (studying)
     * @return - the amount of time that has been spent
     */
    public double getTimeSpent() {
        return this.timeSpent;
    }

    /**
     * Update the amount of time the user has spent preparing (in hours)
     * @param timeSpent - the amount of time spent
     */
    public void setTimeSpent(double timeSpent) {
        this.timeSpent = timeSpent;
    }

    /**
     * Get the amount of time needed to prep
     * @return - the amount of time needed
     */
    public double getTimeNeeded() {
        return this.timeNeeded;
    }

    /**
     * Set the amount of time the user needs to prepare for (in hours)
     * @param timeNeeded - the new time
     */
    public void setTimeNeeded(double timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    /**
     * Get the amount of time the user has left to prepare in hours (before start time)
     * @return - the amount of time the user has remaining
     */
    public double getTimeLeft() {
        return Double.parseDouble(String.valueOf(HOURS.between(LocalDateTime.now(), this.startTime)));
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
     * Set prepTimeScheduled
     * @param prepTimeScheduled - the new list of prep times
     */
    public void setPrepTimeScheduled(ArrayList<ArrayList<LocalDateTime>> prepTimeScheduled) {
        this.prepTimeScheduled = prepTimeScheduled;
    }

    public Test getTestCopy() {
        return new Test(getTitle(), "", startTime, endTime, weightage);
    }
}

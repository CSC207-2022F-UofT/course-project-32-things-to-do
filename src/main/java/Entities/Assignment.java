package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Assignment extends Task implements Gradable, Preparatory {
    // Gradable attributes
    private double weightage = 0;
    private double gradeReceived = -1; // the grade the user receives, -1 if not yet received

    // Preparatory
    private double timeSpent = 0;
    private double timeNeeded = 0;
    private ArrayList<ArrayList<LocalDateTime>> prepTimeScheduled;

    private LocalDateTime dueDate;

    /**
     * Create a new Assignment task with a title and due date
     * @param title - the name of the Assignment
     * @param dueDate - Assignment's due date
     */
    public Assignment(String title, LocalDateTime dueDate) {
        super(title);
        this.dueDate = dueDate;
    }
    /**
     * Create a new Assignment with a title, due date and priority
     * @param title - the name of the Assignment
     * @param priority - the Assignment's priority
     * @param dueDate - the Assignment's due date
     */
    public Assignment(String title, int priority, LocalDateTime dueDate) {
        super(title, priority);
        this.dueDate = dueDate;
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
        // subtract current date from due date
        return 0;
    }

    /**
     * Schedule the required prep time for the Assignment and add to prepTimeScheduled
     * @return - whether scheduling was successful
     */
    public boolean schedulePrepTime() {
        return true;
    }

    /**
     * Get the due date of the Assignment
     * @return - the Assignment's due date
     */
    protected LocalDateTime getDueDate() {
        return this.dueDate;
    }

    protected boolean delete() {
        return true;
    }
    protected boolean save() {
        return true;
    }
    protected void edit() {

    }
}

package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static java.time.temporal.ChronoUnit.HOURS;

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
     * @param id - the unique ID of the Assignment
     * @param dueDate - Assignment's due date
     */
    public Assignment(String title, String id, LocalDateTime dueDate, double weightage) {
        super(title, id);
        this.dueDate = dueDate;
        this.weightage = weightage;
    }
    /**
     * Create a new Assignment with a title, due date and priority
     * @param title - the name of the Assignment
     * @param id - the unique ID of the Assignment
     * @param priority - the Assignment's priority
     * @param dueDate - the Assignment's due date
     */
    public Assignment(String title, String id, int priority, LocalDateTime dueDate, double weightage) {
        super(title, id, priority);
        this.dueDate = dueDate;
        this.weightage = weightage;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
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
     * Retrieve the grade that the user received on the Task
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
     * Get the amount of time spent on the Assignment so far
     * @return - the amount of time that has been spent
     */
    public double getTimeSpent() {
        return this.timeSpent;
    }

    /**
     * Update the amount of time the user has spent preparing (in hours)
     * @param timeSpent - the amount of time being added
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
     * Get the amount of time the user has left to prepare, in hours (before due date)
     * @return - the amount of time the user has remaining
     */
    public double getTimeLeft() {
        return Double.parseDouble(String.valueOf(HOURS.between(LocalDateTime.now(), this.dueDate)));
    }

    /**
     * Schedule the required prep time for the Assignment and add to prepTimeScheduled
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

    /**
     * Get the due date of the Assignment
     * @return - the Assignment's due date
     */
    public LocalDateTime getDueDate() {
        return this.dueDate;
    }
}

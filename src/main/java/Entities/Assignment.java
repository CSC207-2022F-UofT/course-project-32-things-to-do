package Entities;

import java.time.LocalDateTime;
public class Assignment extends Task implements Gradable {
    // Gradable attributes
    private double weightage = 0;
    private double gradeReceived = -1; // the grade the user receives, -1 if not yet received

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

package Entities;

public class Assignment extends Task implements Gradable {
    // Gradable attributes
    private double weightage = 0;
    private double gradeReceived = -1; // the grade the user receives, -1 if not yet received

    public Assignment(String title) {
        super(title);
    }
    public Assignment(String title, int priority) {
        super(title, priority);
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

    protected boolean delete() {
        return true;
    }
    protected boolean save() {
        return true;
    }
    protected void edit() {

    }
}

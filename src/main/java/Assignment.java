public class Assignment extends Task implements Gradable{
    public Assignment(String title) {
        super(title);
    }
    public Assignment(String title, int priority) {
        super(title, priority);
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
     * Set a grade goal for the Test
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

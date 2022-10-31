public interface Gradable {
    double weightage = 0;
    double gradeReceived = -1; // the grade the user receives, -1 if not yet received
    double gradeGoal = 100; // the user's grade goal

    /**
     * Change the weightage of the Gradable Task
     * @param weightage - the new weightage
     */
    void setWeightage(double weightage);

    /**
     * Update the Task with the user's grade
     * @param grade - the grade the user has received
     */
    void setGradeReceived(double grade);

    /**
     * Set a grade goal for a Task
     * @param goal - the grade the user would like to receive
     */
    void setGradeGoal(double goal);
}

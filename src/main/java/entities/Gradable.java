package entities;

public interface Gradable {
    /**
     * Get the weightage of the Test
     * @return - the Test's weightage
     */
    double getWeightage();

    /**
     * Change the weightage of the Task
     * @param weightage - the new weightage
     */
    void setWeightage(double weightage);

    /**
     * Retrieve the grade that the user received on the Task
     * @return - the user's received grade
     */
    double getGradeReceived();

    /**
     * Update the Task with the user's grade
     * @param grade - the grade the user has received
     */
    void setGradeReceived(double grade);

}

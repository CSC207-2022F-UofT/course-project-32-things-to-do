package Entities;

public interface Gradable {
    /**
     * Get the weightage of the Task
     * @return - the weightage of the Task
     */
    double getWeightage();
    /**
     * Change the weightage of the Task
     * @param weightage - the new weightage
     */
    void setWeightage(double weightage);

    /**
     * Get the grade the user received on the Task
     * @return - the grade the user received
     */
    double getGradeReceived();

    /**
     * Update the Task with the user's grade
     * @param grade - the grade the user has received
     */
    void setGradeReceived(double grade);
}

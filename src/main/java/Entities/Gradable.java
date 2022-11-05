package Entities;

public interface Gradable {
    /**
     * Change the weightage of the Entities.Gradable Entities.Task
     * @param weightage - the new weightage
     */
    void setWeightage(double weightage);

    /**
     * Update the Entities.Task with the user's grade
     * @param grade - the grade the user has received
     */
    void setGradeReceived(double grade);
}

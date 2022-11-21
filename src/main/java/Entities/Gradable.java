package entities;

public interface Gradable {
    /**
     * Change the weightage of the Task
     * @param weightage - the new weightage
     */
    void setWeightage(double weightage);

    /**
     * Update the Task with the user's grade
     * @param grade - the grade the user has received
     */
    void setGradeReceived(double grade);
}

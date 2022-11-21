package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Preparatory {
    /**
     * Update the amount of time the user has spent preparing
     * @param timeSpent - the amount of time being added
     */
    void updateTimeSpent(double timeSpent);

    /**
     * Set the amount of time the user needs to prepare for
     * @param timeNeeded - the new time
     */
    void setTimeNeeded(double timeNeeded);

    /**
     * Get the amount of time the user has left to prepare (before due date)
     * @return - the amount of time the user has remaining
     */
    double getTimeLeft();

    /**
     * Schedule the required prep time of the Task and stores it in prepTimeScheduled list
     * @return - whether scheduling was successful
     */
    boolean schedulePrepTime();

    /**
     * Get all scheduled prep times
     * @return - all scheduled prep time
     */
    ArrayList<ArrayList<LocalDateTime>> getPrepTimeScheduled();
}

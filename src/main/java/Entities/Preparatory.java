package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Preparatory {
    /**
     * Get the amount of time spent on the Task so far
     * @return - the amount of time that has been spent
     */
    double getTimeSpent();
    /**
     * Update the amount of time the user has spent preparing (in hours)
     * @param timeSpent - the amount of time spent
     */
    void setTimeSpent(double timeSpent);

    /**
     * Get the amount of time needed to prep
     * @return - the amount of time needed
     */
    double getTimeNeeded();

    /**
     * Set the amount of time the user needs to prepare for (in hours)
     * @param timeNeeded - the new time
     */
    void setTimeNeeded(double timeNeeded);

    /**
     * Get the amount of time the user has left to prepare (before due date)
     * @return - the amount of time the user has remaining (in hours)
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

    /**
     * Set prepTimeScheduled
     * @param prepTimeScheduled - the new list of prep times
     */
    void setPrepTimeScheduled(ArrayList<ArrayList<LocalDateTime>> prepTimeScheduled);
}

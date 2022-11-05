package Entities;

public interface Timeblockable {
    /**
     * Set a new time block
     * @param newTimeBlock - the new time block of the Entities.Timeblockable Entities.Task
     */
    void setTimeBlock(int[] newTimeBlock);

    /**
     * Get the time block of a Entities.Timeblockable Entities.Task
     * @return - the time block of the Entities.Task
     */
    int[] getTimeBlock();

    /**
     * Schedule a time block for the user
     * @return - whether the time block has been successfully scheduled
     */
    boolean scheduleTimeBlock();

    /**
     * Remove a time block from the user's schedule
     * @return - whether the time block has been successfully removed
     */
    boolean removeTimeBlock();
}

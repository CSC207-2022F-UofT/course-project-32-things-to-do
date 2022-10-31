public interface Timeblockable {
    int[] timeBlock = new int[2];

    /**
     * Set a new time block
     * @param newTimeBlock - the new time block of the Timeblockable Task
     */
    void setTimeBlock(int[] newTimeBlock);

    /**
     * Get the time block of a Timeblockable Task
     * @return - the time block of the Task
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

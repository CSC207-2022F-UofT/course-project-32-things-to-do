package Entities;
import java.time.LocalDateTime;
public interface Timeblockable {
    /**
     * Set a new time block
     * @param startTime - the start of the time block
     * @param endTime - the end of the time block
     */
    void setTimeBlock(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * Get the time block of a Timeblockable Task
     * @return - the time block of the Task
     *         - in array form: {startTime, endTime}
     */
     LocalDateTime[] getTimeBlock();

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

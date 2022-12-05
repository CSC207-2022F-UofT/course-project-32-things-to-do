package entities;

import java.time.LocalDateTime;
public class Event extends Task implements Timeblockable {
    private boolean recurring;
    private String frequency;

    // Timeblockable attributes
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Create an Event with a title, priority and time block
     * If the event is recurring, indicate the frequency (eg "daily", "weekly", "monthly)
     * Otherwise, the frequency is blank
     * @param title - the title of the Event
     * @param id - the unique ID of the Event
     * @param priority - the priority value of the Event
     * @param startTime - start of the Event's time block
     * @param endTime - end time of the Event's time block
     * @param recurring - whether the Event is recurring
     * @param frequency - the frequency at which the Event occurs (if recurring)
     */
    public Event(String title, String id, int priority, LocalDateTime startTime, LocalDateTime endTime,
                 boolean recurring, String frequency) {
        super(title, id, priority);
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurring = recurring;
        this.frequency = frequency;
        scheduleTimeBlock();
    }

    /**
     * Set an Event as recurring/not
     * Set its new frequency if it is recurring
     * @param recurring - whether the Event is recurring
     * @param frequency - the frequency at which the Event occurs (if recurring)
     */
    public void setRecurring(boolean recurring, String frequency) {
        this.recurring = recurring;
        if (recurring) this.frequency = frequency;
        else this.frequency = "";
    }

    /**
     * Determine whether the Event is recurring
     * @return - whether the Event is recurring
     */
    public boolean getRecurring() {
        return this.recurring;
    }

    /**
     * Get the frequency of the Event
     * @return - the Event's frequency
     */
    public String getFrequency() {
        return this.frequency;
    }

    /**
     * Set a new time block and then schedule it
     * @param startTime - the start of the time block
     * @param endTime - the end of the time block
     */
    public void setTimeBlock(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        scheduleTimeBlock();
    }

    /**
     * Get the time block of an Event
     * @return - the time block of the Event
     *         - in array form: {startTime, endTime}
     */
    public LocalDateTime[] getTimeBlock() {
        return new LocalDateTime[] {this.startTime, this.endTime};
    }

    /**
     * Schedule a time block for the user
     * @return - whether the time block has been successfully scheduled
     */
    public boolean scheduleTimeBlock() {
        return true;
    }

    /**
     * Remove a time block from the user's schedule
     * @return - whether the time block has been successfully removed
     */
    public boolean removeTimeBlock() {
        return true;
    }
}

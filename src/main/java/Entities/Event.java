package Entities;

import java.time.LocalDateTime;
public class Event extends Task implements Timeblockable {
    private boolean recurring;
    // private LocalDateTime endRecurringBy;
    // ^ implement later
    private String frequency;

    // Timeblockable attributes
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Create an Event with a title
     * If the event is recurring, indicate the frequency (eg "daily", "weekly", "monthly)
     * Otherwise, the frequency is blank
     * @param title - the title of the Event
     * @param id - the unique ID of the Event
     * @param recurring - whether the Event is recurring
     * @param frequency - the frequency at which the Event occurs (if recurring)
     */
    public Event(String title, String id, boolean recurring, String frequency) {
        super(title, id);
        this.recurring = recurring;
        if (recurring) this.frequency = frequency;
        else this.frequency = "";
    }

    /**
     * Create an Event with a title and priority
     * If the event is recurring, indicate the frequency (eg "daily", "weekly", "monthly)
     * Otherwise, the frequency is blank
     * @param title - the title of the Event
     * @param id - the unique ID of the Event
     * @param priority - the priority value of the Event
     * @param recurring - whether the Event is recurring
     * @param frequency - the frequency at which the Event occurs (if recurring)
     */
    public Event(String title, String id, int priority, boolean recurring, String frequency) {
        super(title, id, priority);
        this.recurring = recurring;
        this.frequency = frequency;
    }

    /**
     * Set an Event as recurring/not
     * Set its new frequency if it is recurring
     * @param recurring - whether the Entities.Event is recurring
     * @param frequency - the frequency at which the Entities.Event occurs (if recurring)
     */
    protected void setRecurring(boolean recurring, String frequency) {
        if (recurring) this.frequency = frequency;
    }

    /**
     * Set a new time block
     * @param startTime - the start of the time block
     * @param endTime - the end of the time block
     */
    public void setTimeBlock(LocalDateTime startTime, LocalDateTime endTime) {

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

    /**
     * Delete an Entities.Event by moving it to the user's archive
     * @return - whether the Entities.Event has been successfully deleted
     */
    protected boolean delete() {
        return true;
    }

    /**
     * Save an Entities.Event to the user's data
     * @return - whether the Entities.Event has been successfully saved
     */
    protected boolean save() {
        return true;
    }

    /**
     * Edit the features of the Entities.Event
     */
    protected void edit() {

    }
}

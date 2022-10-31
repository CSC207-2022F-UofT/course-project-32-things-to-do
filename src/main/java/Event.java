public class Event extends Task {
    private boolean recurring;
    private String frequency;

    /**
     * Create an Event with a title
     * If the event is recurring, indicate the frequency (eg "daily", "weekly", "monthly)
     * Otherwise, the frequency is blank
     * @param title - the title of the Event
     * @param recurring - whether the Event is recurring
     * @param frequency - the frequency at which the Event occurs (if recurring)
     */
    public Event(String title, boolean recurring, String frequency) {
        super(title);
        this.recurring = recurring;
        if (recurring) this.frequency = frequency;
        else this.frequency = "";
    }

    /**
     * Create an Event with a title and priority
     * If the event is recurring, indicate the frequency (eg "daily", "weekly", "monthly)
     * Otherwise, the frequency is blank
     * @param title - the title of the Event
     * @param priority - the priority value of the Event
     * @param recurring - whether the Event is recurring
     * @param frequency - the frequency at which the Event occurs (if recurring)
     */
    public Event(String title, int priority, boolean recurring, String frequency) {
        super(title, priority);
        this.recurring = recurring;
        this.frequency = frequency;
    }

    /**
     * Set an Event as recurring/not
     * Set its new frequency if it is recurring
     * @param recurring - whether the Event is recurring
     * @param frequency - the frequency at which the Event occurs (if recurring)
     */
    protected void setRecurring(boolean recurring, String frequency) {
        if (recurring) this.frequency = frequency;
    }

    /**
     * Delete an Event by moving it to the user's archive
     * @return - whether the Event has been successfully deleted
     */
    protected boolean delete() {
        return true;
    }

    /**
     * Save an Event to the user's data
     * @return - whether the Event has been successfully saved
     */
    protected boolean save() {
        return true;
    }

    /**
     * Edit the features of the Event
     */
    protected void edit() {

    }
}

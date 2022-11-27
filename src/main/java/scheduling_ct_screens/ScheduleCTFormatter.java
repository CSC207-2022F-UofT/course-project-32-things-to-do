package scheduling_ct_screens;

import java.util.ArrayList;

/**
 * Model for passing information from ScheduleCTPresenter to ScheduleCTView
 */
public class ScheduleCTFormatter {

    /**
     * The dates and times that will be presented to the user
     */
    private final ArrayList<String> scheduledDateTimes;

    public ScheduleCTFormatter(ArrayList<String> scheduledDateTimes) {
        this.scheduledDateTimes = scheduledDateTimes;
    }

    public ArrayList<String> getScheduledDateTimes() {
        return scheduledDateTimes;
    }

}

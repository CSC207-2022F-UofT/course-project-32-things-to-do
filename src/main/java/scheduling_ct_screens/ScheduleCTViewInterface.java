package scheduling_ct_screens;


/**
 * View Interface for the Scheduling Collaborative Tasks Presenter (ScheduleCTPresenter)
 * (inverts dependency for presenter to view)
 */
public interface ScheduleCTViewInterface {

    /**
     * The method implemented in the view that prepares a screen showing the dates that were scheduled
     * @param scheduleCTFormatter - a scheduleCTFormatter
     */
    void present(ScheduleCTFormatter scheduleCTFormatter);
}

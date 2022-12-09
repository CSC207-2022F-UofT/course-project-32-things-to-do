package screens.collaborative_task_scheduling;

import use_cases.collaborative_task_scheduling.scheduling_ct_use_case.*;

/**
 * View Interface for the Scheduling Collaborative Tasks Presenter (ScheduleCTPresenter)
 * (inverts dependency for presenter to view)
 */
public interface ScheduleCTViewInterface {

    /**
     * The method implemented in the view that prepares a screen showing the dates that were scheduled
     * @param responseModel - a scheduleCTFormatter
     */
    void present(ScheduleCTResponseModel responseModel);
}
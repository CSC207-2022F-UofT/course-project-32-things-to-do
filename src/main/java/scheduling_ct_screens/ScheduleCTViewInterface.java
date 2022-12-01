package scheduling_ct_screens;


import scheduling_ct_use_case.ScheduleCTResponseModel;

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

package screens.collaborative_task_scheduling;

import use_cases.collaborative_task_scheduling.scheduling_ct_use_case.*;

/**
 * Presenter for the Collaborative Scheduling Use Case
 * Implements the Output Boundary as part of the dependency inversion
 */

public class ScheduleCTPresenter implements ScheduleCTOutputBoundary {

    ScheduleCTViewInterface scheduleCTViewInterface;

    public ScheduleCTPresenter(ScheduleCTViewInterface scheduleCTViewInterface) {
        this.scheduleCTViewInterface = scheduleCTViewInterface;
    }

    /**
     * Implemented method from ScheduleCTOutputBoundary which calls on the View Interface to update the view
     * @param responseModel - a scheduleCTResponseModel
     * @return a scheduleCTResponseModel
     */
    @Override
    public ScheduleCTResponseModel prepareNoConflictView(ScheduleCTResponseModel responseModel) {

        scheduleCTViewInterface.present(responseModel);

        return null;
    }

    /**
     * Implemented method from ScheduleCTOutputBoundary that prepares the fail view
     * @param error - the type of error that happened
     * @return a SchedulingTimesFailed error with the specific error
     */
    @Override
    public ScheduleCTResponseModel prepareFailView(String error) {
        throw new SchedulingTimesFailed(error);
    }
}
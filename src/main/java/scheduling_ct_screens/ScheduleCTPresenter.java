package scheduling_ct_screens;
import scheduling_ct_use_case.*;

import javax.swing.*;

/**
 * Presenter for the Collaborative Scheduling Use Case
 * Implements the Output Boundary as part of the dependency inversion
 */

public class ScheduleCTPresenter implements ScheduleCTOutputBoundary {

    public ScheduleCTPresenter() {
    }

    /**
     * The implementation of prepareNoConflictView from the Output Boundary that prepares the view for when there is no
     * conflict (shows pop-up screen with "successful input")
     * @param responseModel - a scheduleCTResponseModel
     * @return the ScheduleCTResponseModel
     */
    @Override
    public ScheduleCTResponseModel prepareNoConflictView(ScheduleCTResponseModel responseModel) {
        JOptionPane.showMessageDialog(null, "Successful input");
        return responseModel;
    }

    /**
     * The implementation of prepareFailView from the Output Boundary that prepares the view for when there is a
     * conflict (run-time error with the error specified)
     * @param error - the type of error that happened
     * @return the ScheduleCTResponseModel
     */
    @Override
    public ScheduleCTResponseModel prepareFailView(String error) {
        throw new SchedulingTimesFailed(error);
    }
}
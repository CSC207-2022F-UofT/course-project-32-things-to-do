package scheduling_ct_screens;
import scheduling_ct_use_case.*;

/**
 * Presenter for the Collaborative Scheduling Use Case
 * Implements the Output Boundary as part of the dependency inversion
 */

public class ScheduleCTPresenter implements ScheduleCTOutputBoundary {

    @Override
    public ScheduleCTResponseModel prepareNoConflictView(ScheduleCTResponseModel responseModel) {
        if ((!responseModel.getIsConflict())) {
            responseModel.setDisplayString("Successful input");
        }
        return responseModel;
    }

    @Override
    public ScheduleCTResponseModel prepareFailView(String error) {
        throw new SchedulingTimesFailed(error);
    }
}

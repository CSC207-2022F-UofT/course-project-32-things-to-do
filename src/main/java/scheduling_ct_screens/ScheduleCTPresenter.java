package scheduling_ct_screens;
import scheduling_ct_use_case.*;

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

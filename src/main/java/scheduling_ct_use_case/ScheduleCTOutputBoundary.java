package scheduling_ct_use_case;

public interface ScheduleCTOutputBoundary {

    ScheduleCTResponseModel prepareNoConflictView(ScheduleCTResponseModel responseModel);

    ScheduleCTResponseModel prepareFailView(String error);
}

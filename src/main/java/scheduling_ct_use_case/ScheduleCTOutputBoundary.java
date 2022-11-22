package scheduling_ct_use_case;

/**
 * Output Boundary for the Scheduling Collaborative Tasks Use Case
 * (inverts dependency for interactor to presenter)
 */

public interface ScheduleCTOutputBoundary {

    ScheduleCTResponseModel prepareNoConflictView(ScheduleCTResponseModel responseModel);

    ScheduleCTResponseModel prepareFailView(String error);
}

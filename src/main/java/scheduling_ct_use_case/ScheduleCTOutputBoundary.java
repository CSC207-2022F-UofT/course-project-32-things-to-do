package scheduling_ct_use_case;

/**
 * Output Boundary for the Scheduling Collaborative Tasks Use Case
 * (inverts dependency for interactor to presenter)
 */

public interface ScheduleCTOutputBoundary {

    /**
     * The method implemented in the presenter that prepares the no conflict view when there is no conflict
     * @param responseModel - a scheduleCTResponseModel
     * @return a scheduleCTResponseModel
     */
    ScheduleCTResponseModel prepareNoConflictView(ScheduleCTResponseModel responseModel);

    /**
     * The method implemented in the presenter that prepares the fail view for when there is conflict
     * @param error - the type of error that happened
     * @return a scheduleCTResponseModel
     */
    ScheduleCTResponseModel prepareFailView(String error);
}
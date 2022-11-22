package scheduler_use_case;

public interface SchedulerPresenter {

    /**
     * Alert the user of the task scheduling failure
     * @param responseModel - the output from the program
     */
    SchedulerResponseModel prepareFailView(SchedulerResponseModel responseModel);

    /**
     * Alert the user of the task scheduling success
     * @param responseModel - the output from the program
     */
    SchedulerResponseModel prepareSuccessView(SchedulerResponseModel responseModel);
}

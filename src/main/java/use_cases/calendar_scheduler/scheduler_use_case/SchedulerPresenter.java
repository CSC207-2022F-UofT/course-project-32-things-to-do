package use_cases.calendar_scheduler.scheduler_use_case;

public interface SchedulerPresenter {

    /**
     * Alert the user of the task scheduling success
     * @param responseModel - the output from the scheduling use case
     */
    SchedulerResponseModel prepareSuccessView(SchedulerResponseModel responseModel);

    /**
     * Alert the user of the task scheduling failure
     * @param error - the given error
     */
    SchedulerResponseModel prepareFailView(String error);
}

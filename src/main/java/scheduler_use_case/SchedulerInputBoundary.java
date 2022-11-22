package scheduler_use_case;

public interface SchedulerInputBoundary {

    /**
     * Interface for the input of the Scheduler use case
     * @param requestModel - the given request model
     */
    SchedulerResponseModel schedule(SchedulerRequestModel requestModel);
}

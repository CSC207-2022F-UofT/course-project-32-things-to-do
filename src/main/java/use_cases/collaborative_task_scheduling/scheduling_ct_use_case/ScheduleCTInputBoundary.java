package use_cases.collaborative_task_scheduling.scheduling_ct_use_case;


/**
 * Input Boundary interface for the Scheduling Collaborative Tasks Use Case
 * (inverts dependency from Controller to Interactor)
 */

public interface ScheduleCTInputBoundary {

    /**
     * The method implemented in ScheduleCTInteractor that schedules the task given the inputs
     * @param scheduleCTRequestModel - the scheduleCTRequestModel (i.e. information given from the user)
     * @return a scheduleCTResponseModel
     */
    ScheduleCTResponseModel schedule(ScheduleCTRequestModel scheduleCTRequestModel);
}
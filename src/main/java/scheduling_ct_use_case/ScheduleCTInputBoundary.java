package scheduling_ct_use_case;

import entities.Task;
import scheduler_use_case.SchedulerInteractor;

import java.util.HashMap;

/**
 * Input Boundary interface for the Scheduling Collaborative Tasks Use Case
 * (inverts dependency from Controller to Interactor)
 */

public interface ScheduleCTInputBoundary {

    /**
     * The method implemented in ScheduleCTInteractor that schedules the task given the inputs
     * @param scheduleCTRequestModel - the scheduleCTRequestModel (i.e. information given from the user)
     * @param hashMap - hash map of task ids mapped to Task objects
     * @param schedulerInteractor - schedulerInteractor from scheduler_use_case in order to add to calendar
     * @return a scheduleCTResponseModel
     */
    ScheduleCTResponseModel schedule(ScheduleCTRequestModel scheduleCTRequestModel, HashMap<String, Task> hashMap,
                                     SchedulerInteractor schedulerInteractor);
}
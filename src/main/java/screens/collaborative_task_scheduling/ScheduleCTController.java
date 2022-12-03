package screens.collaborative_task_scheduling;

import use_cases.collaborative_task_scheduling.scheduling_ct_use_case.ScheduleCTInputBoundary;
import use_cases.collaborative_task_scheduling.scheduling_ct_use_case.ScheduleCTRequestModel;


/**
 * Controller for the Scheduling Collaborative Tasks Use Case
 * Triggers the interactor
 */

public class ScheduleCTController {

    final ScheduleCTInputBoundary scheduleInput;

    private final Object studentUser;

    /**
     * Constructor for ScheduleCTController
     * @param scheduleInput - the scheduleCTInputBoundary
     * @param studentUser - the current student user logged in
     */
    public ScheduleCTController(ScheduleCTInputBoundary scheduleInput, Object studentUser) {
        this.scheduleInput = scheduleInput;
        this.studentUser = studentUser;
    }

    /**
     * Bundles information given by user as well as ScheduleCTController instance variables into a
     * ScheduleCTRequestModel, scheduling that input
     *
     * @param taskName  - the string title/name of the task
     * @param startTime - the string representation of the start date and time the user wants to schedule
     * @param endTime   - the string representation of the end date and time the user wants to schedule
     */
    public void isConflict(String taskName, String startTime, String endTime) {
        ScheduleCTRequestModel inputData = new ScheduleCTRequestModel(taskName, startTime, endTime, studentUser);
        scheduleInput.schedule(inputData);
    }
}
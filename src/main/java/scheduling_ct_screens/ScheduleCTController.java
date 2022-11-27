package scheduling_ct_screens;
import entities.StudentUser;
import entities.Task;
import scheduling_ct_use_case.*;

import java.util.HashMap;

/**
 * Controller for the Scheduling Collaborative Tasks Use Case
 * Triggers the interactor
 */

public class ScheduleCTController {

    final ScheduleCTInputBoundary scheduleInput;

    private final HashMap<String, Task> hashMap;

    private final StudentUser studentUser;

    /**
     * Constructor for ScheduleCTController
     * @param scheduleInput - the scheduleCTInputBoundary
     * @param hashMap - a hash map of all task ids mapped to the task object
     * @param studentUser - the current student user logged in
     */
    public ScheduleCTController(ScheduleCTInputBoundary scheduleInput, HashMap<String, Task> hashMap, StudentUser studentUser) {
        this.scheduleInput = scheduleInput;
        this.hashMap = hashMap;
        this.studentUser = studentUser;
    }

    /**
     * Bundles information given by user as well as ScheduleCTController instance variables into a
     * ScheduleCTRequestModel, scheduling that input
     * @param taskName - the string title/name of the task
     * @param startTime - the string representation of the start date and time the user wants to schedule
     * @param endTime - the string representation of the end date and time the user wants to schedule
     * @return a scheduleCTResponseModel
     */
    public ScheduleCTResponseModel isConflict(String taskName, String startTime, String endTime) {
        ScheduleCTRequestModel inputData = new ScheduleCTRequestModel(taskName, startTime, endTime, studentUser);
        return scheduleInput.schedule(inputData, this.hashMap);
    }

}

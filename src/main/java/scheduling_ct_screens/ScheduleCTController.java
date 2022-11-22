package scheduling_ct_screens;
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

    public ScheduleCTController(ScheduleCTInputBoundary scheduleInput, HashMap<String, Task> hashMap) {
        this.scheduleInput = scheduleInput;
        this.hashMap = hashMap;
    }

    public HashMap<String, Task> getTaskMap() {
        return hashMap;
    }

    public ScheduleCTResponseModel isConflict(String taskName, String username, String startTime, String endTime) {
        ScheduleCTRequestModel inputData = new ScheduleCTRequestModel(taskName, username, startTime, endTime);
        return scheduleInput.schedule(inputData, this.hashMap);
    }

}

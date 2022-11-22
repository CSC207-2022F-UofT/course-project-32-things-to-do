package scheduling_ct_screens;
import entities.Task;
import scheduling_ct_use_case.*;
import entities.TaskMap;

import java.util.HashMap;

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

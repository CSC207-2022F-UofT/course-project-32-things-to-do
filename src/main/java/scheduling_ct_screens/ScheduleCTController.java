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

    public ScheduleCTController(ScheduleCTInputBoundary scheduleInput, HashMap<String, Task> hashMap, StudentUser studentUser) {
        this.scheduleInput = scheduleInput;
        this.hashMap = hashMap;
        this.studentUser = studentUser;
    }

    public HashMap<String, Task> getTaskMap() {
        return hashMap;
    }

    public StudentUser getStudentUser() {
        return studentUser;
    }

    public ScheduleCTResponseModel isConflict(String taskName, String startTime, String endTime) {
        ScheduleCTRequestModel inputData = new ScheduleCTRequestModel(taskName, startTime, endTime, studentUser);
        return scheduleInput.schedule(inputData, this.hashMap);
    }

}

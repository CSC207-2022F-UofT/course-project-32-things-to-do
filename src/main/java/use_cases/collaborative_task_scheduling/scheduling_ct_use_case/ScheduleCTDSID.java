package use_cases.collaborative_task_scheduling.scheduling_ct_use_case;

import entities.Task;
import entities.TaskMap;

import java.util.HashMap;

public class ScheduleCTDSID {

    private HashMap<String, Task> updatedHashMap;

    public ScheduleCTDSID(HashMap<String, Task> updatedHashMap) {
        this.updatedHashMap = updatedHashMap;
    }

    public HashMap<String, Task> getUpdatedTaskMapDSID() {
        return this.updatedHashMap;
    }
    public void setUpdatedTaskMapDSID(HashMap<String, Task> updatedHashMap) {
        this.updatedHashMap = updatedHashMap;
    }
}

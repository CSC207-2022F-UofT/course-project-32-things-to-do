package scheduling_ct_use_case;

import entities.Task;
import java.util.HashMap;

/**
 * Input Boundary interface for the Scheduling Collaborative Tasks Use Case
 * (inverts dependency from Controller to Interactor)
 */

public interface ScheduleCTInputBoundary {

    ScheduleCTResponseModel schedule(ScheduleCTRequestModel scheduleCTRequestModel, HashMap<String, Task> hashMap);
}

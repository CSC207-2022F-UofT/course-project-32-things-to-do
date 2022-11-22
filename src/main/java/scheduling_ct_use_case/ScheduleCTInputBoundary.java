package scheduling_ct_use_case;

import entities.Task;
import java.util.HashMap;

// use case layer

public interface ScheduleCTInputBoundary {

    ScheduleCTResponseModel schedule(ScheduleCTRequestModel scheduleCTRequestModel, HashMap<String, Task> hashMap);
}

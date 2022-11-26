package scheduling_ct_use_case;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Response Model for the Scheduling Collaborative Tasks Use Case
 * Acts as the output data object in the use case layer
 */

public class ScheduleCTResponseModel {

    private final boolean isConflict;
    ArrayList<ArrayList<LocalDateTime>> timesToSchedule;

    public ScheduleCTResponseModel(boolean isConflict) {

        this.isConflict = isConflict;
    }

    public void setTimesToSchedule(ArrayList<ArrayList<LocalDateTime>> timesToSchedule) {
        this.timesToSchedule = timesToSchedule;
    }

}

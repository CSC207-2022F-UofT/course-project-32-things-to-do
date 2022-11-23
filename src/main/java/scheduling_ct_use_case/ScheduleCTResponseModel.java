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
    String displayString;

    public ScheduleCTResponseModel(boolean isConflict) {

        this.isConflict = isConflict;
    }

    public boolean getIsConflict() {
        return isConflict;
    }

    public String getDisplayString() {
        return displayString;
    }
    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public void setTimesToSchedule(ArrayList<ArrayList<LocalDateTime>> timesToSchedule) {
        this.timesToSchedule = timesToSchedule;
    }
}

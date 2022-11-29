package use_cases.collaborative_task_scheduling.scheduling_ct_use_case;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Response Model for the Scheduling Collaborative Tasks Use Case
 * Acts as the output data object in the use case layer
 */

public class ScheduleCTResponseModel {

    private final boolean isConflict;
    private final ArrayList<String> scheduledTimes;
    ArrayList<ArrayList<LocalDateTime>> timesToSchedule;

    public ScheduleCTResponseModel(boolean isConflict, ArrayList<String> scheduledTimes) {

        this.isConflict = isConflict;
        this.scheduledTimes = scheduledTimes;
    }

    public ArrayList<String> getScheduledTimes() {
        return scheduledTimes;
    }

    public void setTimesToSchedule(ArrayList<ArrayList<LocalDateTime>> timesToSchedule) {
        this.timesToSchedule = timesToSchedule;
    }
}

package use_case_collaborative_scheduling;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CollaborativeSchedulingResponseModel {

    private final ArrayList<ArrayList<LocalDateTime>> availableTimes;
    ArrayList<String> scheduleString;

    public CollaborativeSchedulingResponseModel(ArrayList<ArrayList<LocalDateTime>> availableTimes) {
        this.availableTimes = availableTimes;
    }

    public ArrayList<ArrayList<LocalDateTime>> getAvailableTimes() {
        return availableTimes;
    }

    public void setScheduleString(ArrayList<String> scheduleString) {
        this.scheduleString = scheduleString;
    }
    public ArrayList<String> getScheduleString() {
        return this.scheduleString;
    }
}

package use_case_schedule_ct;

import entities.CollaborativeTask;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ScheduleTaskRequestModel {

    private final CollaborativeTask task;
    private final ArrayList<ArrayList<LocalDateTime>> scheduleTimes;

    public ScheduleTaskRequestModel(CollaborativeTask task, ArrayList<ArrayList<LocalDateTime>> scheduleTimes) {
        this.task = task;
        this.scheduleTimes = scheduleTimes;
    }

    public CollaborativeTask getTask(){
        return task;
    }

    public ArrayList<ArrayList<LocalDateTime>> getScheduleTimes(){
        return scheduleTimes;
    }


}

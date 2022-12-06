package use_cases.calendar_scheduler.working_hours_use_case;

import entities.StudentUser;

import java.time.LocalTime;
import java.util.ArrayList;

public class WorkingHoursRequestModel {

    private ArrayList<LocalTime> workingHours;

    public WorkingHoursRequestModel(ArrayList<LocalTime> workingHours) {
        this.workingHours = workingHours;
    }

    public ArrayList<LocalTime> getWorkingHours() {
        return workingHours;
    }
}

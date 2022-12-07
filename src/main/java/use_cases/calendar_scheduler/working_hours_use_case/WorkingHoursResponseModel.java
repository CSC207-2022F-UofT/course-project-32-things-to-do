package use_cases.calendar_scheduler.working_hours_use_case;

import java.time.LocalTime;
import java.util.ArrayList;

public class WorkingHoursResponseModel {

    private ArrayList<LocalTime> workingHours;

    public WorkingHoursResponseModel(ArrayList<LocalTime> workingHours) {

        this.workingHours = workingHours;

    }

    public ArrayList<LocalTime> getWorkingHours() {
        return workingHours;
    }
}

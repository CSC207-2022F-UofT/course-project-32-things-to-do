package screens.calendar_scheduler;

import use_cases.calendar_scheduler.working_hours_use_case.*;

import java.time.LocalTime;
import java.util.ArrayList;

public class WorkingHoursController {

    WorkingHoursInputBoundary workingHoursInteractor;

    public WorkingHoursController() {
        this.workingHoursInteractor = new WorkingHoursInteractor();
    }

    void setWorkingHours(ArrayList<LocalTime> workingHours) {

        WorkingHoursRequestModel requestModel = new WorkingHoursRequestModel(workingHours);
        workingHoursInteractor.setWorkingHours(requestModel);
    }

}

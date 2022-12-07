package use_cases.calendar_scheduler.working_hours_use_case;

import java.time.LocalTime;
import java.util.ArrayList;

public interface WorkingHoursOutputBoundary {

    ArrayList<LocalTime> getWorkingHours();
}

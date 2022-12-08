package screens.calendar_scheduler;

import use_cases.calendar_scheduler.working_hours_use_case.*;

import java.time.LocalTime;
import java.util.ArrayList;

public class WorkingHoursPresenter implements WorkingHoursOutputBoundary {

    @Override
    public ArrayList<LocalTime> getWorkingHours() {

        WorkingHoursInteractor interactor = new WorkingHoursInteractor();
        WorkingHoursResponseModel responseModel = interactor.getWorkingHours();

        return responseModel.getWorkingHours();
    }
}

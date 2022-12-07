package use_cases.calendar_scheduler.working_hours_use_case;

import entities.CurrentUser;
import entities.StudentUser;

import java.time.LocalTime;
import java.util.ArrayList;

public class WorkingHoursInteractor implements WorkingHoursInputBoundary {

    private StudentUser user;

    public WorkingHoursInteractor() {
        this.user = (StudentUser) CurrentUser.getCurrentUser();
    }

    @Override
    public void setWorkingHours(WorkingHoursRequestModel requestModel) {
        user.setWorkingHours(requestModel.getWorkingHours());
    }

    public WorkingHoursResponseModel getWorkingHours() {
        WorkingHoursResponseModel responseModel = new WorkingHoursResponseModel(user.getWorkingHours());
        return responseModel;
    }
}

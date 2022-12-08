package use_cases.calendar_scheduler.working_hours_use_case;

import entities.*;

public class WorkingHoursInteractor implements WorkingHoursInputBoundary {

    private final StudentUser user;

    public WorkingHoursInteractor() {
        this.user = (StudentUser) CurrentUser.getCurrentUser();
    }

    @Override
    public void setWorkingHours(WorkingHoursRequestModel requestModel) {
        user.setWorkingHours(requestModel.getWorkingHours());
    }

    public WorkingHoursResponseModel getWorkingHours() {
        return new WorkingHoursResponseModel(user.getWorkingHours());
    }
}

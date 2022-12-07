package calendar_scheduler_use_case;

import entities.*;
import org.junit.jupiter.api.Test;
import screens.calendar_scheduler.ScheduleConflictPresenter;
import screens.calendar_scheduler.WorkingHoursPresenter;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictOutputBoundary;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictRequestModel;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictResponseModel;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerInputBoundary;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerInteractor;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerRequestModel;
import use_cases.calendar_scheduler.working_hours_use_case.WorkingHoursInteractor;
import use_cases.calendar_scheduler.working_hours_use_case.WorkingHoursOutputBoundary;
import use_cases.calendar_scheduler.working_hours_use_case.WorkingHoursRequestModel;
import use_cases.calendar_scheduler.working_hours_use_case.WorkingHoursResponseModel;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class WorkingHoursInteractorTest {

    @Test
    void testWorkingHours() {
        WorkingHoursOutputBoundary workingHoursPresenter = new WorkingHoursPresenter() {
            @Override
            public ArrayList<LocalTime> getWorkingHours() {

                WorkingHoursInteractor interactor = new WorkingHoursInteractor();
                WorkingHoursResponseModel responseModel = interactor.getWorkingHours();

                ArrayList<LocalTime> expectedWorkingHours = new ArrayList<>();
                expectedWorkingHours.add(LocalTime.parse("08:00"));
                expectedWorkingHours.add(LocalTime.parse("21:00"));

                assertEquals(expectedWorkingHours, responseModel.getWorkingHours());

                return null;
            }
        };

        // Create interactor and test entities
        StudentUser user = new StudentUser("testUser", "testPassword");
        CurrentUser.setCurrentUser(user);
        WorkingHoursInteractor interactor = new WorkingHoursInteractor();

        ArrayList<LocalTime> workingHours = new ArrayList<>();
        workingHours.add(LocalTime.parse("08:00"));
        workingHours.add(LocalTime.parse("21:00"));

        // Test changing working hours
        WorkingHoursRequestModel requestModel = new WorkingHoursRequestModel(workingHours);
        interactor.setWorkingHours(requestModel);

        ArrayList<LocalTime> expectedWorkingHours = new ArrayList<>();
        expectedWorkingHours.add(LocalTime.parse("08:00"));
        expectedWorkingHours.add(LocalTime.parse("21:00"));
        WorkingHoursResponseModel responseModel = interactor.getWorkingHours();

        assertEquals(expectedWorkingHours, responseModel.getWorkingHours());
    }

}
package test_calendar_scheduler;

import entities.*;
import org.junit.jupiter.api.Test;
import use_cases.calendar_scheduler.working_hours_use_case.*;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WorkingHoursInteractorTest {

    @Test
    void testWorkingHours() {

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
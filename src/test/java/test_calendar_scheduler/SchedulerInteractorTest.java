package test_calendar_scheduler;

import entities.*;
import org.junit.jupiter.api.Test;
import screens.calendar_scheduler.*;
import use_cases.calendar_scheduler.schedule_conflict_use_case.*;
import use_cases.calendar_scheduler.scheduler_use_case.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerInteractorTest  {

    /**
     * Test the scheduling of a task with conflict
     */
    @Test
    void testConflictSchedule() {

        ScheduleConflictOutputBoundary scheduleConflictPresenter = new ScheduleConflictPresenter() {
            @Override
            public ScheduleConflictResponseModel alertConflict(ScheduleConflictRequestModel requestModel) {
                assertEquals("testEvent1", requestModel.getConflictingTask().getTitle());
                return new ScheduleConflictResponseModel(false);
            }
        };

        // Create interactor and test entities
        SchedulerInputBoundary interactor = new SchedulerInteractor(scheduleConflictPresenter);
        StudentUser user = new StudentUser("testUser", "testPassword");

        Event event1 = new Event("testEvent1", "testid1", 0, LocalDateTime.of(2022, 12, 10, 14, 0),
                LocalDateTime.of(2022, 12, 10, 15, 0), false, null);
        Event event2 = new Event("testEvent2", "testid2", 0, LocalDateTime.of(2022, 12, 10, 14, 30),
                LocalDateTime.of(2022, 12, 10, 15, 30), false, null);

        // Test scheduling of conflicting task
        SchedulerRequestModel requestModel1 = new SchedulerRequestModel(event1, user);
        interactor.schedule(requestModel1);

        user.addTaskToList("testEvent1");
        HashMap<String, Task> testMap = new HashMap<>();
        testMap.put("testEvent1", event1);
        TaskMap.setTaskMap(testMap);

        SchedulerRequestModel requestModel2 = new SchedulerRequestModel(event2, user);
        interactor.schedule(requestModel2);
    }

    /**
     * Test the scheduling of prep time
     * NOTE: only works during working hours (7am - 11pm) due to issues coordinating current time
     */
    @Test
    void testPrepTimeScheduling() {
        // Create anonymous implementing class for the output boundary
        ScheduleConflictOutputBoundary scheduleConflictPresenter = new ScheduleConflictPresenter() {
            @Override
            public ScheduleConflictResponseModel alertConflict(ScheduleConflictRequestModel requestModel) {
                return new ScheduleConflictResponseModel(false);
            }
        };

        // Create interactor and test entities
        SchedulerInputBoundary interactor = new SchedulerInteractor(scheduleConflictPresenter);
        StudentUser user = new StudentUser("testUser", "testPassword");
        LocalDateTime currDateTime = LocalDateTime.now();

        Event event1 = new Event("testEvent1", "testID1", 0, currDateTime,
                currDateTime.plusHours(1), false, null);
        Event event2 = new Event("testEvent2", "testID2", 0, currDateTime.plusHours(2),
                currDateTime.plusHours(3), false, null);
        Assignment assignment = new Assignment("testAssignment", "assignmentID", currDateTime.plusDays(1), 0.0);
        assignment.setTimeNeeded(60.0);

        // Test scheduling of prep time
        SchedulerRequestModel requestModel1 = new SchedulerRequestModel(event1, user);
        interactor.schedule(requestModel1);

        SchedulerRequestModel requestModel2 = new SchedulerRequestModel(event2, user);
        interactor.schedule(requestModel2);

        user.addTaskToList("testEvent1");
        user.addTaskToList("testEvent2");
        HashMap<String, Task> testMap = new HashMap<>();
        testMap.put("testEvent1", event1);
        testMap.put("testEvent2", event2);
        TaskMap.setTaskMap(testMap);

        SchedulerRequestModel requestModel3 = new SchedulerRequestModel(assignment, user);
        interactor.schedule(requestModel3);

        ArrayList<ArrayList<LocalDateTime>> expectedPrepTime = new ArrayList<>();

        ArrayList<LocalDateTime> prepTime = new ArrayList<>();
        LocalDateTime prepStart = currDateTime.plusHours(1);
        LocalDateTime prepEnd = currDateTime.plusHours(2);
        prepTime.add(prepStart);
        prepTime.add(prepEnd);

        expectedPrepTime.add(prepTime);

        assertEquals(expectedPrepTime, (assignment.getPrepTimeScheduled()));
    }

}
package scheduler_use_case;

import entities.*;
import org.junit.jupiter.api.Test;
import screens.calendar_scheduler.ScheduleConflictPresenter;
import screens.calendar_scheduler.SchedulerPresenter;
import use_cases.calendar_scheduler.schedule_conflict_use_case.*;
import use_cases.calendar_scheduler.scheduler_use_case.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerInteractorTest {

    /**
     * Test the scheduling of a task with no conflict
     */
    @Test
    void testNoConflictSchedule() {
        // Create anonymous implementing class for the output boundary
        SchedulerOutputBoundary schedulerOutputBoundary = new SchedulerOutputBoundary() {
            @Override
            public SchedulerResponseModel prepareSuccessView(SchedulerResponseModel responseModel) {
                return null;
            }

            @Override
            public SchedulerResponseModel prepareFailView(String error) {
                fail("Scheduling failed");
                return null;
            }
        };
        ScheduleConflictOutputBoundary scheduleConflictOutputBoundary = new ScheduleConflictPresenter();

        // Create interactor and test entities
        SchedulerInputBoundary interactor = new SchedulerInteractor(scheduleConflictOutputBoundary, schedulerOutputBoundary);
        StudentUser user = new StudentUser("testUser", "testPassword");

        Event event1 = new Event("testEvent1", "testid1", 0, LocalDateTime.of(2022, 12, 10, 14, 0),
                LocalDateTime.of(2022, 12, 10, 15, 0), false, "No");

        // Test scheduling of task
        SchedulerRequestModel requestModel1 = new SchedulerRequestModel(event1, user);
        interactor.schedule(requestModel1);
    }

    /**
     * Test the scheduling of a task with conflict
     */
    @Test
    void testConflictSchedule() {
        // Create anonymous implementing class for the output boundary
        SchedulerOutputBoundary schedulerOutputBoundary = new SchedulerPresenter();
        ScheduleConflictOutputBoundary scheduleConflictOutputBoundary = new ScheduleConflictOutputBoundary() {
            @Override
            public ScheduleConflictResponseModel alertConflict(ScheduleConflictRequestModel requestModel) {
                assertEquals("testEvent1", requestModel.getConflictingTask().getTitle());
                return null;
            }
        };

        // Create interactor and test entities
        SchedulerInputBoundary interactor = new SchedulerInteractor(scheduleConflictOutputBoundary, schedulerOutputBoundary);
        StudentUser user = new StudentUser("testUser", "testPassword");

        Event event1 = new Event("testEvent1", "testid1", 0, LocalDateTime.of(2022, 12, 10, 14, 0),
                LocalDateTime.of(2022, 12, 10, 15, 0), false, "No");
        Event event2 = new Event("testEvent2", "testid2", 0, LocalDateTime.of(2022, 12, 10, 14, 30),
                LocalDateTime.of(2022, 12, 10, 15, 30), false, "No");

        // Test scheduling of conflicting task
        SchedulerRequestModel requestModel1 = new SchedulerRequestModel(event1, user);
        interactor.schedule(requestModel1);

        SchedulerRequestModel requestModel2 = new SchedulerRequestModel(event2, user);
        interactor.schedule(requestModel2);
    }

    @Test
    void testPrepTimeScheduling() {
        // Create anonymous implementing class for the output boundary
        SchedulerOutputBoundary schedulerOutputBoundary = new SchedulerOutputBoundary() {
            @Override
            public SchedulerResponseModel prepareSuccessView(SchedulerResponseModel responseModel) {
                Task task = responseModel.getTask();
                ArrayList<ArrayList<LocalDateTime>> expectedPrepTime = new ArrayList<>();

                ArrayList<LocalDateTime> prepTime = new ArrayList<>();
                LocalDateTime prepStart = LocalDateTime.of(2022, 12, 10, 15, 0);
                LocalDateTime prepEnd = LocalDateTime.of(2022, 12, 10, 16, 0);
                prepTime.add(prepStart);
                prepTime.add(prepEnd);

                expectedPrepTime.add(prepTime);

                if (task instanceof Preparatory) {
                    assertEquals(expectedPrepTime, ((Preparatory) task).getPrepTimeScheduled());
                }
                return null;
            }

            @Override
            public SchedulerResponseModel prepareFailView(String error) {
                fail("Scheduling failed");
                return null;
            }
        };
        ScheduleConflictOutputBoundary scheduleConflictOutputBoundary = new ScheduleConflictPresenter();

        // Create interactor and test entities
        SchedulerInputBoundary interactor = new SchedulerInteractor(scheduleConflictOutputBoundary, schedulerOutputBoundary);
        StudentUser user = new StudentUser("testUser", "testPassword");

        Event event1 = new Event("testEvent1", "testID1", 0, LocalDateTime.of(2022, 12, 10, 14, 0),
                LocalDateTime.of(2022, 12, 10, 15, 0), false, "No");
        Event event2 = new Event("testEvent2", "testID2", 0, LocalDateTime.of(2022, 12, 10, 16, 0),
                LocalDateTime.of(2022, 12, 10, 16, 30), false, "No");
        Assignment assignment = new Assignment("testAssignment", "assignmentID", LocalDateTime.of(2022, 12, 11, 0, 0), 0.0);
        assignment.setTimeNeeded(60.0);

        // Test scheduling of conflicting task
        SchedulerRequestModel requestModel1 = new SchedulerRequestModel(event1, user);
        interactor.schedule(requestModel1);

        SchedulerRequestModel requestModel2 = new SchedulerRequestModel(event2, user);
        interactor.schedule(requestModel2);

        SchedulerRequestModel requestModel3 = new SchedulerRequestModel(assignment, user);
        interactor.schedule(requestModel3);
    }

}
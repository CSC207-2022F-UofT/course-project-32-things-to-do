package scheduler_use_case;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.calendar_scheduler.schedule_conflict_use_case.*;
import use_cases.calendar_scheduler.scheduler_use_case.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerInteractorTest {

    @Test
    void schedule() {
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
                LocalDateTime.of(2022, 12, 10, 15, 0), false, null);
        Event event2 = new Event("testEvent2", "testid2", 0, LocalDateTime.of(2022, 12, 10, 14, 30),
                LocalDateTime.of(2022, 12, 10, 15, 30), false, null);

        // Test scheduling of conflicting task
        SchedulerRequestModel requestModel1 = new SchedulerRequestModel(event1, user);
        interactor.schedule(requestModel1);

        SchedulerRequestModel requestModel2 = new SchedulerRequestModel(event2, user);
        interactor.schedule(requestModel2);
    }

}
//package scheduler_use_case;
//
//import entities.*;
//import org.junit.jupiter.api.Test;
//import schedule_conflict_use_case.ScheduleConflictPresenter;
//import screens.ScheduleConflictResponseFormatter;
//import screens.SchedulerResponseFormatter;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SchedulerInteractorTest {
//
//    @Test
//    void schedule() {
//        // Initialise the classes
//        SchedulerPresenter schedulerPresenter = new SchedulerResponseFormatter();
//        ScheduleConflictPresenter scheduleConflictPresenter = new ScheduleConflictResponseFormatter();
//        SchedulerInputBoundary interactor = new SchedulerInteractor(scheduleConflictPresenter, schedulerPresenter);
//
//        // Create the test user and task
//        Event event = new Event("testEvent", "testid", 0, LocalDateTime.of(2022, 11, 29, 14, 0),
//                LocalDateTime.of(2022, 11, 29, 15, 0), false, null);
//        StudentUser user = new StudentUser("testUser", "testPassword");
//
//        // Test the interactor
//        SchedulerRequestModel requestModel = new SchedulerRequestModel(event, user);
//        interactor.schedule(requestModel);
//
//
//    }
//
//}
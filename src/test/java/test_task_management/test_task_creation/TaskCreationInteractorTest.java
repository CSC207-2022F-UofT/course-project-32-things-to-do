package test_task_management.test_task_creation;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screens.calendar_scheduler.*;
import screens.task_management.*;
import use_cases.calendar_scheduler.schedule_conflict_use_case.*;
import use_cases.task_management.read_write.*;
import use_cases.task_management.task_creation_use_case.*;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TaskCreationInteractorTest {

    @BeforeEach
    void setUp() {
        CurrentUser.setCurrentUser(new StudentUser("name", "abc"));
        TaskMap.setTaskMap(new HashMap<>());
    }

    /**
     * test creating an assignment
     */
    @Test
    void createAssignment() {
        // To test the use case:
        // 1) Create a TaskCreationInteractor and prerequisite objects
        //    (arguments for the TaskCreationInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case Task Creation Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        InMemoryTaskMap taskMapGateway = new InMemoryTaskMap();

        // This creates an anonymous implementing class for the Output Boundary.
        TaskCreationOutputBoundary outputBoundary = new TaskCreationOutputBoundary() {
            @Override
            public TaskCreationResponseModel prepareSuccessView(TaskCreationResponseModel response) {
                // 4) Check that the Output Data and associated changes
                assertEquals(response.getTitle(), "title");
                assertEquals(response.getType(), "Assignment");
                assertTrue(taskMapGateway.existsById(response.getId()));
                return null;
            }

            @Override
            public TaskCreationResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        AssignmentCreationRequestModel aRequest = new AssignmentCreationRequestModel("title", 0, LocalDateTime.of(2022, 12, 3, 12, 0), 20.5);

        ScheduleConflictOutputBoundary scheduleConflictOutputBoundary = new ScheduleConflictPresenter();
        TaskCreationInputBoundary interactor = new TaskCreationInteractor(taskMapGateway, outputBoundary, "none", scheduleConflictOutputBoundary);

        interactor.create(aRequest, "Assignment");
    }

    /**
     * test creating a test
     */
    @Test
    void createTest() {
        // To test the use case:
        // 1) Create a TaskCreationInteractor and prerequisite objects
        //    (arguments for the TaskCreationInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case Task Creation Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        TaskMapGateway taskMapGateway = new InMemoryTaskMap();

        // This creates an anonymous implementing class for the Output Boundary.
        TaskCreationOutputBoundary outputBoundary = new TaskCreationOutputBoundary() {
            @Override
            public TaskCreationResponseModel prepareSuccessView(TaskCreationResponseModel response) {
                // 4) Check that the Output Data and associated changes
                assertEquals(response.getTitle(), "title");
                assertEquals(response.getType(), "Test");
                assertTrue((taskMapGateway).existsById(response.getId()));
                return null;
            }

            @Override
            public TaskCreationResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        TestCreationRequestModel tRequest = new TestCreationRequestModel(
                "title", 10, LocalDateTime.of(2022, 12, 2, 12, 0),
                LocalDateTime.of(2022, 12, 2, 13, 0), 0);

        ScheduleConflictOutputBoundary scheduleConflictOutputBoundary = new ScheduleConflictPresenter();
        TaskCreationInputBoundary interactor = new TaskCreationInteractor(taskMapGateway, outputBoundary, "none", scheduleConflictOutputBoundary);

        interactor.create(tRequest, "Test");
    }

    /**
     * test creating an event
     */
    @Test
    void createEvent() {
        // To test the use case:
        // 1) Create a TaskCreationInteractor and prerequisite objects
        //    (arguments for the TaskCreationInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case Task Creation Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        TaskMapGateway taskMapGateway = new InMemoryTaskMap();

        // This creates an anonymous implementing class for the Output Boundary.
        TaskCreationOutputBoundary outputBoundary = new TaskCreationOutputBoundary() {
            @Override
            public TaskCreationResponseModel prepareSuccessView(TaskCreationResponseModel response) {
                // 4) Check that the Output Data and associated changes
                assertEquals(response.getTitle(), "title");
                assertEquals(response.getType(), "Event");
                assertTrue((taskMapGateway).existsById(response.getId()));
                return null;
            }

            @Override
            public TaskCreationResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        EventCreationRequestModel eRequest = new EventCreationRequestModel("title", 0,
                LocalDateTime.of(2022, 12, 2, 12, 0),
                LocalDateTime.of(2022, 12, 2, 13, 0),
                false, "");

        ScheduleConflictOutputBoundary scheduleConflictOutputBoundary = new ScheduleConflictPresenter();
        TaskCreationInputBoundary interactor = new TaskCreationInteractor(taskMapGateway, outputBoundary, "none", scheduleConflictOutputBoundary);

        interactor.create(eRequest, "Event");
    }
}
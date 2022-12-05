package use_cases.task_management.task_edit_use_case;

import entities.Assignment;
import entities.CurrentUser;
import entities.StudentUser;
import entities.TaskMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import screens.task_management.InMemoryTaskMap;
import use_cases.task_management.read_write.TaskMapGateway;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TaskEditInteractorTest {
    LocalDateTime startTime = LocalDateTime.of(2022, 12, 3, 12, 0);
    LocalDateTime endTime = LocalDateTime.of(2022, 12, 3, 13, 0);

    static LocalDateTime oldStart = LocalDateTime.of(2022, 12, 4, 12, 0);
    static LocalDateTime oldEnd = LocalDateTime.of(2022, 12, 4, 13, 0);
    @BeforeAll
    static void beforeAll() {
        // set up for testing
        CurrentUser.setCurrentUser(new StudentUser("name", "abc"));
        ((StudentUser)CurrentUser.getCurrentUser()).addTaskToList("a");
        ((StudentUser)CurrentUser.getCurrentUser()).addTaskToList("t");
        ((StudentUser)CurrentUser.getCurrentUser()).addTaskToList("e");

        TaskMap.setTaskMap(new HashMap<>());
        TaskMap.addTask("a", new Assignment("title", "a", 10, oldStart, 20.0));
        TaskMap.addTask("t", new entities.Test("title", "t", 10, oldStart, oldEnd, 20.0));
        TaskMap.addTask("e", new entities.Event("title", "e", 10, oldStart, oldEnd, true, "monthly"));
    }

    /**
     * test editing an assignment
     */
    @Test
    void editAssignmentIncomplete() {
        // To test the use case:
        // 1) Create a TaskEditInteractor and prerequisite objects
        //    (arguments for the TaskEditInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case Task Edit Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        TaskMapGateway taskMapGateway = new InMemoryTaskMap();

        // This creates an anonymous implementing class for the Output Boundary.
        TaskEditPresenter presenter = new TaskEditPresenter() {

            @Override
            public TaskEditResponseModel prepareSuccessView(TaskEditResponseModel responseModel) {
                // 4) Check that the Output Data and associated changes
                assertEquals(responseModel.getTitle(), "title");
                assertEquals(responseModel.getType(), "Assignment");
                Assignment a = (Assignment) TaskMap.findTask("a");
                assertFalse(a.getComplete());
                assertEquals(a.getDueDate(), startTime);
                assertEquals(a.getWeightage(), 30.0);
                assertEquals(a.getTimeNeeded(), 10);
                assertEquals(a.getTimeSpent(), 2);
                assertTrue(((StudentUser)CurrentUser.getCurrentUser()).getToDoList().contains("a"));
                return null;
            }

            @Override
            public TaskEditResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        TaskEditRequestModel aRequest = new AssignmentEditRequestModel("a", false, 0,
                LocalDateTime.of(2022, 12, 3, 12, 0), 30.0, 10, 2);

        TaskEditInputBoundary interactor = new TaskEditInteractor(taskMapGateway, presenter);

        interactor.edit(aRequest, "Assignment");
    }

    /**
     * test editing an assignment and marking it as complete
     */
    @Test
    void editAssignmentComplete() {
        // To test the use case:
        // 1) Create a TaskEditInteractor and prerequisite objects
        //    (arguments for the TaskEditInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case Task Edit Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        TaskMapGateway taskMapGateway = new InMemoryTaskMap();

        // This creates an anonymous implementing class for the Output Boundary.
        TaskEditPresenter presenter = new TaskEditPresenter() {

            @Override
            public TaskEditResponseModel prepareSuccessView(TaskEditResponseModel responseModel) {
                // 4) Check that the Output Data and associated changes
                assertEquals(responseModel.getTitle(), "title");
                assertEquals(responseModel.getType(), "Assignment");
                Assignment a = (Assignment) TaskMap.findTask("a");
                assertTrue(a.getComplete());
                assertFalse(((StudentUser)CurrentUser.getCurrentUser()).getToDoList().contains("a"));
                assertTrue(((StudentUser)CurrentUser.getCurrentUser()).getTaskArchive().contains("a"));
                return null;
            }

            @Override
            public TaskEditResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        TaskEditRequestModel aRequestComp = new AssignmentEditRequestModel("a", true, 0,
                startTime, 30.0, 10, 2);

        TaskEditInputBoundary interactor = new TaskEditInteractor(taskMapGateway, presenter);

        interactor.edit(aRequestComp, "Assignment");
    }

    /**
     * test editing a test
     */
    @Test
    void editTestIncomplete() {
        // To test the use case:
        // 1) Create a TaskEditInteractor and prerequisite objects
        //    (arguments for the TaskEditInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case Task Edit Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        TaskMapGateway taskMapGateway = new InMemoryTaskMap();

        // This creates an anonymous implementing class for the Output Boundary.
        TaskEditPresenter presenter = new TaskEditPresenter() {

            @Override
            public TaskEditResponseModel prepareSuccessView(TaskEditResponseModel responseModel) {
                // 4) Check that the Output Data and associated changes
                assertEquals(responseModel.getTitle(), "title");
                assertEquals(responseModel.getType(), "Test");
                entities.Test t = (entities.Test) TaskMap.findTask("t");
                assertFalse(t.getComplete());
                assertEquals(t.getTimeBlock()[0], startTime);
                assertEquals(t.getTimeBlock()[1], endTime);
                assertEquals(t.getWeightage(), 30.0);
                assertEquals(t.getTimeNeeded(), 10);
                assertEquals(t.getTimeSpent(), 2);
                assertTrue(((StudentUser)CurrentUser.getCurrentUser()).getToDoList().contains("t"));
                return null;
            }

            @Override
            public TaskEditResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        TaskEditRequestModel tRequest = new TestEditRequestModel("t", false, 0,
                startTime, endTime, 30.0, 10, 2);

        TaskEditInputBoundary interactor = new TaskEditInteractor(taskMapGateway, presenter);

        interactor.edit(tRequest, "Test");
    }

    /**
     * test editing a test and marking it as complete
     */
    @Test
    void editTestComplete() {
        // To test the use case:
        // 1) Create a TaskEditInteractor and prerequisite objects
        //    (arguments for the TaskEditInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case Task Edit Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        TaskMapGateway taskMapGateway = new InMemoryTaskMap();

        // This creates an anonymous implementing class for the Output Boundary.
        TaskEditPresenter presenter = new TaskEditPresenter() {

            @Override
            public TaskEditResponseModel prepareSuccessView(TaskEditResponseModel responseModel) {
                // 4) Check that the Output Data and associated changes
                assertEquals(responseModel.getTitle(), "title");
                assertEquals(responseModel.getType(), "Test");
                entities.Test t = (entities.Test) TaskMap.findTask("t");
                assertTrue(t.getComplete());
                assertFalse(((StudentUser)CurrentUser.getCurrentUser()).getToDoList().contains("t"));
                assertTrue(((StudentUser)CurrentUser.getCurrentUser()).getTaskArchive().contains("t"));
                return null;
            }

            @Override
            public TaskEditResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        TaskEditRequestModel tRequestComp = new TestEditRequestModel("t", true, 0,
                startTime, endTime, 30.0, 10, 2);

        TaskEditInputBoundary interactor = new TaskEditInteractor(taskMapGateway, presenter);

        interactor.edit(tRequestComp, "Test");
    }

    /**
     * test editing an event
     */
    @Test
    void editEventIncomplete() {
        // To test the use case:
        // 1) Create a TaskEditInteractor and prerequisite objects
        //    (arguments for the TaskEditInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case Task Edit Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        TaskMapGateway taskMapGateway = new InMemoryTaskMap();

        // This creates an anonymous implementing class for the Output Boundary.
        TaskEditPresenter presenter = new TaskEditPresenter() {

            @Override
            public TaskEditResponseModel prepareSuccessView(TaskEditResponseModel responseModel) {
                // 4) Check that the Output Data and associated changes
                assertEquals(responseModel.getTitle(), "title");
                assertEquals(responseModel.getType(), "Event");
                entities.Event e = (entities.Event) TaskMap.findTask("e");
                assertFalse(e.getComplete());
                assertEquals(e.getTimeBlock()[0], startTime);
                assertEquals(e.getTimeBlock()[1], endTime);
                assertFalse(e.getRecurring());
                assertEquals(e.getFrequency(), "");
                assertTrue(((StudentUser)CurrentUser.getCurrentUser()).getToDoList().contains("e"));
                return null;
            }

            @Override
            public TaskEditResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        TaskEditRequestModel eRequest = new EventEditRequestModel("e", false, 0,
                startTime, endTime, false, "");

        TaskEditInputBoundary interactor = new TaskEditInteractor(taskMapGateway, presenter);

        interactor.edit(eRequest, "Event");
    }

    /**
     * test editing an event and marking it as complete
     */
    @Test
    void editEventComplete() {
        // To test the use case:
        // 1) Create a TaskEditInteractor and prerequisite objects
        //    (arguments for the TaskEditInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case Task Edit Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        TaskMapGateway taskMapGateway = new InMemoryTaskMap();

        // This creates an anonymous implementing class for the Output Boundary.
        TaskEditPresenter presenter = new TaskEditPresenter() {

            @Override
            public TaskEditResponseModel prepareSuccessView(TaskEditResponseModel responseModel) {
                // 4) Check that the Output Data and associated changes
                assertEquals(responseModel.getTitle(), "title");
                assertEquals(responseModel.getType(), "Event");
                entities.Event e = (entities.Event) TaskMap.findTask("e");
                assertTrue(e.getComplete());
                assertFalse(((StudentUser)CurrentUser.getCurrentUser()).getToDoList().contains("e"));
                assertTrue(((StudentUser)CurrentUser.getCurrentUser()).getTaskArchive().contains("e"));
                return null;
            }

            @Override
            public TaskEditResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        TaskEditRequestModel eRequestComp = new EventEditRequestModel("e", true, 0,
                startTime, endTime, false, "");

        TaskEditInputBoundary interactor = new TaskEditInteractor(taskMapGateway, presenter);

        interactor.edit(eRequestComp, "Event");
    }
}
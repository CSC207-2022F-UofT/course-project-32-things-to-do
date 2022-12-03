package use_cases.task_management.task_deletion_use_case;

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

class TaskDeletionInteractorTest {

    @BeforeAll
    static void beforeAll() {
        CurrentUser.setCurrentUser(new StudentUser("name", "abc"));
        TaskMap.setTaskMap(new HashMap<>());
        TaskMap.addTask("id", new Assignment(
                "title", "id", 0, LocalDateTime.now(), 20));
        ((StudentUser)CurrentUser.getCurrentUser()).addTaskToList("id");
    }

    @Test
    void delete() {
        // To test the use case:
        // 1) Create a TaskDeletionInteractor and prerequisite objects
        //    (arguments for the TaskDeletionInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case Task Deletion Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        TaskMapGateway taskMapGateway = new InMemoryTaskMap();

        // This creates an anonymous implementing class for the Output Boundary.
        TaskDeletionPresenter presenter = t -> {
            // 4) Check that the Output Data and associated changes
            assertEquals(t.getTitle(), "title");
            assertEquals(t.getId(), "id");
            assertFalse(((StudentUser)CurrentUser.getCurrentUser()).getToDoList().contains("id"));

            return null;
        };
        TaskDeletionRequestModel requestModel = new TaskDeletionRequestModel("id");

        TaskDeletionInputBoundary interactor = new TaskDeletionInteractor(taskMapGateway, presenter);

        interactor.delete(requestModel);
    }
}
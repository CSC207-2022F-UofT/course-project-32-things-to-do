package use_cases.task_management.task_creation_use_case;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskCreationResponseModelTest {
    static TaskCreationResponseModel response;
    @BeforeAll
    static void beforeAll() {
        response = new TaskCreationResponseModel("title", "id", "Assignment");
    }

    @Test
    void getTitle() {
        assertEquals(response.getTitle(), "title");
    }

    @Test
    void getId() {
        assertEquals(response.getId(), "id");
    }

    @Test
    void getType() {
        assertEquals(response.getType(), "Assignment");
    }
}
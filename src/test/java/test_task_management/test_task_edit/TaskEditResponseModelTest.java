package test_task_management.test_task_edit;

import org.junit.jupiter.api.Test;
import use_cases.task_management.task_edit_use_case.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskEditResponseModelTest {
    static TaskEditResponseModel response = new TaskEditResponseModel("title", "id", "Event");

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
        assertEquals(response.getType(), "Event");
    }
}
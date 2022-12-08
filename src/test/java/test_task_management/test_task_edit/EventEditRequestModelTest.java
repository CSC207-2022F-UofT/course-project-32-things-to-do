package test_task_management.test_task_edit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_cases.task_management.task_edit_use_case.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventEditRequestModelTest {
    static TaskEditRequestModel requestModel;

    @BeforeAll
    static void beforeAll() {
        requestModel = new EventEditRequestModel("id", true, 10,
                LocalDateTime.of(2022, 12, 2, 12, 0),
                LocalDateTime.of(2022, 12, 2, 13, 0), true, "monthly");
    }

    @Test
    void getComplete() {
        assertTrue(requestModel.getComplete());
    }

    @Test
    void getId() {
        assertEquals(requestModel.getId(), "id");
    }

    @Test
    void getPriority() {
        assertEquals(requestModel.getPriority(), 10);
    }
    @Test
    void getStartTime() {
        assertEquals(((EventEditRequestModel)requestModel).getStartTime(), LocalDateTime.of(2022, 12, 2, 12, 0));
    }

    @Test
    void getEndTime() {
        assertEquals(((EventEditRequestModel)requestModel).getEndTime(), LocalDateTime.of(2022, 12, 2, 13, 0));
    }

    @Test
    void getRecurring() {
        assertTrue(((EventEditRequestModel)requestModel).getRecurring());
    }

    @Test
    void getFrequency() {
        assertEquals(((EventEditRequestModel)requestModel).getFrequency(), "monthly");
    }
}
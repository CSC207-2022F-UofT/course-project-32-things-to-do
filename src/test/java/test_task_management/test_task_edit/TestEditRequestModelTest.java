package test_task_management.test_task_edit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_cases.task_management.task_edit_use_case.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TestEditRequestModelTest {
    static TaskEditRequestModel requestModel;

    @BeforeAll
    static void beforeAll() {
        requestModel = new TestEditRequestModel("id", false, 20,
                LocalDateTime.of(2022, 12, 2, 12, 0),
                LocalDateTime.of(2022, 12, 2, 13, 0),
                30.6, 40, 15);
    }

    @Test
    void getComplete() {
        assertFalse(requestModel.getComplete());
    }

    @Test
    void getId() {
        assertEquals(requestModel.getId(), "id");
    }

    @Test
    void getPriority() {
        assertEquals(requestModel.getPriority(), 20);
    }
    @Test
    void getStartTime() {
        assertEquals(((TestEditRequestModel)requestModel).getStartTime(), LocalDateTime.of(2022, 12, 2, 12, 0));
    }

    @Test
    void getEndTime() {
        assertEquals(((TestEditRequestModel)requestModel).getEndTime(), LocalDateTime.of(2022, 12, 2, 13, 0));
    }

    @Test
    void getWeightage() {
        assertEquals(((TestEditRequestModel)requestModel).getWeightage(), 30.6);
    }

    @Test
    void getTimeNeeded() {
        assertEquals(((TestEditRequestModel)requestModel).getTimeNeeded(), 40);
    }

    @Test
    void getTimeSpent() {
        assertEquals(((TestEditRequestModel)requestModel).getTimeSpent(), 15);
    }
}
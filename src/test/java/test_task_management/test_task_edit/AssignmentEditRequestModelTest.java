package test_task_management.test_task_edit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_cases.task_management.task_edit_use_case.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentEditRequestModelTest {
    static TaskEditRequestModel requestModel;

    @BeforeAll
    static void beforeAll() {
        requestModel = new AssignmentEditRequestModel("id", false, 0,
                LocalDateTime.of(2022, 12, 2, 12, 0), 10,
                11, 2);
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
        assertEquals(requestModel.getPriority(), 0);
    }
    @Test
    void getDueDate() {
        assertEquals(((AssignmentEditRequestModel)requestModel).getDueDate(), LocalDateTime.of(2022, 12, 2, 12, 0));
    }

    @Test
    void getWeightage() {
        assertEquals(((AssignmentEditRequestModel)requestModel).getWeightage(), 10);
    }

    @Test
    void getTimeNeeded() {
        assertEquals(((AssignmentEditRequestModel)requestModel).getTimeNeeded(), 11);
    }

    @Test
    void getTimeSpent() {
        assertEquals(((AssignmentEditRequestModel)requestModel).getTimeSpent(), 2);
    }
}
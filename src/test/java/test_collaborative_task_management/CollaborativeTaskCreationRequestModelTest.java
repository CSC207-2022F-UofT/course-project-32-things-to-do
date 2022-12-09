package test_collaborative_task_management;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_cases.collaborative_task_management.collaborative_task_creation_use_case.CollaborativeTaskCreationRequestModel;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CollaborativeTaskCreationRequestModelTest {

    static CollaborativeTaskCreationRequestModel requestModel;

    @BeforeAll
    static void beforeAll() {
        // initialize new request model
        LocalDateTime startTime = LocalDateTime.of(2022, 12, 2, 4, 38);
        LocalDateTime endTime = LocalDateTime.of(2022, 12, 4, 4, 38);
        LocalDateTime deadline = LocalDateTime.of(2022, 12, 7, 4, 38);
        requestModel = new CollaborativeTaskCreationRequestModel("requestModel", 0, false, "", startTime, endTime, deadline);
    }

    @Test
    void getRecurring() {assertFalse(requestModel.getRecurring()); }

    @Test
    void getFrequency() {assertEquals(requestModel.getFrequency(), "");}

    @Test
    void getStartTime() {
        LocalDateTime startTime = LocalDateTime.of(2022, 12, 2, 4, 38);
        assertEquals(requestModel.getStartTime(), startTime);
    }

    @Test
    void getEndTime() {
        LocalDateTime endTime = LocalDateTime.of(2022, 12, 4, 4, 38);
        assertEquals(requestModel.getEndTime(), endTime);
    }

    @Test
    void getDeadline() {
        LocalDateTime deadline = LocalDateTime.of(2022, 12, 7, 4, 38);
        assertEquals(requestModel.getDeadline(), deadline);
    }
}

package test_task_management.test_task_creation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_cases.task_management.task_creation_use_case.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentCreationRequestModelTest {
    static TaskCreationRequestModel assignmentCreationRequestModel;
    @BeforeAll
    static void beforeAll() {
        assignmentCreationRequestModel = new AssignmentCreationRequestModel("title", 0, LocalDateTime.of(2022, 12, 3, 12, 0), 20.5);
    }
    @Test
    void getTitle() {
        assertEquals(assignmentCreationRequestModel.getTitle(), "title");
    }

    @Test
    void getPriority() {
        assertEquals(assignmentCreationRequestModel.getPriority(), 0);
    }

    @Test
    void getDueDate() {
        assertEquals(((AssignmentCreationRequestModel)assignmentCreationRequestModel).getDueDate(), LocalDateTime.of(2022, 12, 3, 12, 0));
    }

    @Test
    void getWeightage() {
        assertEquals(((AssignmentCreationRequestModel)assignmentCreationRequestModel).getWeightage(), 20.5);
    }
}
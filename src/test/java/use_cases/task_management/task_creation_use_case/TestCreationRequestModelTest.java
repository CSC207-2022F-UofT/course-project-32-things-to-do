package use_cases.task_management.task_creation_use_case;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TestCreationRequestModelTest {
    static TaskCreationRequestModel testCreationRequestModel;
    @BeforeAll
    static void beforeAll() {
        testCreationRequestModel = new TestCreationRequestModel(
                "title", 10, LocalDateTime.of(2022, 12, 2, 12, 0),
                LocalDateTime.of(2022, 12, 2, 13, 0), 0);
    }

    @Test
    void getTitle() {
        assertEquals(testCreationRequestModel.getTitle(), "title");
    }

    @Test
    void getPriority() {
        assertEquals(testCreationRequestModel.getPriority(), 10);
    }
    @Test
    void getStartTime() {
        assertEquals(((TestCreationRequestModel)testCreationRequestModel).getStartTime(), LocalDateTime.of(2022, 12, 2, 12, 0));
    }

    @Test
    void getEndTime() {
        assertEquals(((TestCreationRequestModel)testCreationRequestModel).getEndTime(), LocalDateTime.of(2022, 12, 2, 13, 0));
    }

    @Test
    void getWeightage() {
        assertEquals(((TestCreationRequestModel)testCreationRequestModel).getWeightage(), 0);
    }
}
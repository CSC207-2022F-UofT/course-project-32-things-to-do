package use_cases.task_management.task_edit_use_case;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TestInfoRetrieverTest {
    static entities.Test test;
    static TestDisplayer testInfo;

    @BeforeAll
    static void beforeAll() {
        LocalDateTime startTime = LocalDateTime.of(2022, 12, 2, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 12, 2, 13, 0);
        test = new entities.Test("title", "id", 10, startTime, endTime, 30.0);

        testInfo = new TestInfoRetriever(test);
    }

    @Test
    void getTitle() {
        assertEquals(testInfo.getTitle(), "title");
    }

    @Test
    void getId() {
        assertEquals(testInfo.getId(), "id");
    }

    @Test
    void getPriority() {
        assertEquals(testInfo.getPriority(), 10);
    }

    @Test
    void getStartTime() {
        assertEquals(testInfo.getStartTime(), LocalDateTime.of(2022, 12, 2, 12, 0));
    }

    @Test
    void getEndTime() {
        assertEquals(testInfo.getEndTime(), LocalDateTime.of(2022, 12, 2, 13, 0));
    }

    @Test
    void getWeightage() {
        assertEquals(testInfo.getWeightage(), 30);
    }

    @Test
    void getTimeNeeded() {
        assertEquals(testInfo.getTimeNeeded(), 0);
    }

    @Test
    void getTimeSpent() {
        assertEquals(testInfo.getTimeSpent(), 0);
    }
}
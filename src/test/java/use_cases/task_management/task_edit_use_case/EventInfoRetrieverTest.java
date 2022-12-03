package use_cases.task_management.task_edit_use_case;

import entities.Event;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventInfoRetrieverTest {
    static Event event;
    static EventDisplayer eventInfo;
    @BeforeAll
    static void beforeAll() {
        LocalDateTime startDate = LocalDateTime.of(2022, 12, 2, 4, 38);
        LocalDateTime endDate = LocalDateTime.of(2022, 12, 2, 6, 0);
        event = new Event(
                "title", "id", 1, startDate, endDate, false, "");

        eventInfo = new EventInfoRetriever("id");
    }

    @Test
    void getTitle() {
        assertEquals(eventInfo.getTitle(), "title");
    }

    @Test
    void getId() {
        assertEquals(eventInfo.getId(), "id");
    }

    @Test
    void getPriority() {
        assertEquals(eventInfo.getPriority(), 1);
    }

    @Test
    void getStartTime() {
        assertEquals(eventInfo.getStartTime(), LocalDateTime.of(2022, 12, 2, 4, 38));
    }

    @Test
    void getEndTime() {
        assertEquals(eventInfo.getEndTime(), LocalDateTime.of(2022, 12, 2, 6, 0));
    }

    @Test
    void getRecurring() {
        assertFalse(eventInfo.getRecurring());
    }

    @Test
    void getFrequency() {
        assertEquals(eventInfo.getFrequency(), "");
    }
}
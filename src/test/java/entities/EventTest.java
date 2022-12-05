package entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    static Event event;
    @BeforeAll
    static void beforeAll() {
        // initialize new assignment
        LocalDateTime startDate = LocalDateTime.of(2022, 12, 2, 4, 38);
        LocalDateTime endDate = LocalDateTime.of(2022, 12, 2, 6, 0);
        event = new Event(
                "e1", "e1_name_course", 1, startDate, endDate, false, "");
    }

    @Test
    void getId() {
        assertEquals(event.getId(), "e1_name_course");
    }

    @Test
    void setId() {
        event.setId("just e_name_course");
        assertEquals(event.getId(), "just e_name_course");
    }

    @Test
    void getAndSetTitle() {
        assertEquals(event.getTitle(), "e1");

        event.setTitle("just e");
        assertEquals(event.getTitle(), "just e");
    }

    @Test
    void getAndSetPriority() {
        assertEquals(event.getPriority(), 1);

        event.setPriority(10);
        assertEquals(event.getPriority(), 10);
    }

    @Test
    void getAndSetComplete() {
        assertFalse(event.getComplete());

        event.setComplete();
        assertTrue(event.getComplete());
    }

    @Test
    void getAndSetRecurring() {
        assertFalse(event.getRecurring());

        event.setRecurring(true, "monthly");
        assertTrue(event.getRecurring());
    }

    @Test
    void getFrequency() {
        assertEquals(event.getFrequency(), "monthly");
    }

    @Test
    void getTimeBlock() {
        assertEquals(event.getTimeBlock()[0], LocalDateTime.of(2022, 12, 2, 4, 38));
        assertEquals(event.getTimeBlock()[1], LocalDateTime.of(2022, 12, 2, 6, 0));
    }

    @Test
    void setTimeBlock() {
        LocalDateTime newStart = LocalDateTime.of(2022, 12, 3, 12, 0);
        LocalDateTime newEnd = LocalDateTime.of(2022, 12, 3, 13, 0);
        event.setTimeBlock(newStart, newEnd);

        assertEquals(event.getTimeBlock()[0], newStart);
        assertEquals(event.getTimeBlock()[1], newEnd);
    }
}
package use_cases.task_management.task_creation_use_case;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventCreationRequestModelTest {
    static TaskCreationRequestModel eventCreationRequestModel;

    @BeforeAll
    static void beforeAll() {
        eventCreationRequestModel = new EventCreationRequestModel("title", 0,
                LocalDateTime.of(2022, 12, 2, 12, 0),
                LocalDateTime.of(2022, 12, 2, 13, 0),
                false, "");
    }

    @Test
    void getTitle() {
        assertEquals(eventCreationRequestModel.getTitle(), "title");
    }

    @Test
    void getPriority() {
        assertEquals(eventCreationRequestModel.getPriority(), 0);
    }

    @Test
    void getStartTime() {
        assertEquals(((EventCreationRequestModel) eventCreationRequestModel).getStartTime(), LocalDateTime.of(2022, 12, 2, 12, 0));
    }

    @Test
    void getEndTime() {
        assertEquals(((EventCreationRequestModel) eventCreationRequestModel).getEndTime(), LocalDateTime.of(2022, 12, 2, 13, 0));
    }

    @Test
    void getRecurring() {
        assertFalse(((EventCreationRequestModel) eventCreationRequestModel).getRecurring());
    }

    @Test
    void getFrequency() {
        assertEquals(((EventCreationRequestModel) eventCreationRequestModel).getFrequency(), "");
    }
}
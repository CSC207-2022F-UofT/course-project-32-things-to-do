package use_cases.collaborative_task_management.collaborative_task_creation_use_case;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class CollaborativeTaskCreationRequestModelTest {
    static CollaborativeTaskCreationRequestModel collaborativeTaskCreationRequestModel;
    @BeforeAll
    static void beforeAll() {
        collaborativeTaskCreationRequestModel = new CollaborativeTaskCreationRequestModel("title", 10, true, "weekly",  LocalDateTime.of(2022, 12, 2, 12, 0),  LocalDateTime.of(2022, 12, 2, 12, 30),  LocalDateTime.of(2022, 12, 2, 13, 0));
    }

    @Test
    void getTitle() {
        assertEquals(collaborativeTaskCreationRequestModel.getTitle(), "title");
    }

    @Test
    void getPriority() {
        assertEquals(collaborativeTaskCreationRequestModel.getPriority(), 10);
    }

    @Test
    void getRecurring() {
        assertTrue(collaborativeTaskCreationRequestModel.getRecurring());
    }

    @Test
    void getFrequency() {
        assertEquals(collaborativeTaskCreationRequestModel.getFrequency(), "weekly");
    }

    @Test
    void getStartTime() {assertEquals(((CollaborativeTaskCreationRequestModel)collaborativeTaskCreationRequestModel).getStartTime(), LocalDateTime.of(2022, 12, 2, 12, 0));}

    @Test
    void getEndTime() {assertEquals(((CollaborativeTaskCreationRequestModel)collaborativeTaskCreationRequestModel).getEndTime(), LocalDateTime.of(2022, 12, 2, 12, 30));}

    @Test
    void getDeadline() {assertEquals(((CollaborativeTaskCreationRequestModel)collaborativeTaskCreationRequestModel).getDeadline(), LocalDateTime.of(2022, 12, 2, 13, 0));}


}

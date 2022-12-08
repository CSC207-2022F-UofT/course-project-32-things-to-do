package use_cases.collaborative_task_management.collaborative_task_creation_use_case;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollaborativeTaskCreationResponseModelTest {
    static CollaborativeTaskCreationResponseModel response;
    @BeforeAll
    static void beforeAll() {
        response = new CollaborativeTaskCreationResponseModel("title", "id");
    }

    @Test
    void getTitle() {
        assertEquals(response.getTitle(), "title");
    }

    @Test
    void getId() {
        assertEquals(response.getId(), "id");
    }

    @Test
    void getType() {
        assertEquals(response.getType(), "Collaborative");
    }
}


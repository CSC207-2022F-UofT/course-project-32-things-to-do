package test_collaborative_task_management;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_cases.collaborative_task_management.collaborative_task_creation_use_case.CollaborativeTaskCreationResponseModel;

import static org.junit.jupiter.api.Assertions.*;

class CollaborativeTaskCreationResponseModelTest {

    static CollaborativeTaskCreationResponseModel responseModel;

    @BeforeAll
    static void beforeAll() {
        // initialize new response model
        responseModel = new CollaborativeTaskCreationResponseModel("requestModel", "requestModelID");
    }

    @Test
    void getTitle() {
        assertEquals(responseModel.getTitle(), "requestModel");
    }

    @Test
    void getId() {
        assertEquals(responseModel.getId(), "requestModelID");
    }

    @Test
    void getType() {
        assertEquals(responseModel.getType(), "Collaborative");

    }
}
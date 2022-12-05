package use_cases.task_management.task_deletion_use_case;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskDeletionResponseModelTest {
    TaskDeletionResponseModel taskDeletionResponseModel = new TaskDeletionResponseModel("title", "id");
    @Test
    void getTitle() {
        assertEquals(taskDeletionResponseModel.getTitle(), "title");
    }

    @Test
    void getId() {
        assertEquals(taskDeletionResponseModel.getId(), "id");
    }
}
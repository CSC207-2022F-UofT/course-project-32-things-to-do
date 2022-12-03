package use_cases.task_management.task_edit_use_case;

import entities.Assignment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentInfoRetrieverTest {
    static Assignment assignment;
    static AssignmentDisplayer assignmentInfo;

    @BeforeAll
    static void beforeAll() {
        LocalDateTime date = LocalDateTime.of(2022, 12, 2, 4, 38);
        assignment = new Assignment(
                "title", "id", 1, date, 20.0);

        assignmentInfo = new AssignmentInfoRetriever(assignment);
    }

    @Test
    void getTitle() {
        assertEquals(assignmentInfo.getTitle(), "title");
    }

    @Test
    void getId() {
        assertEquals(assignmentInfo.getId(), "id");
    }

    @Test
    void getPriority() {
        assertEquals(assignmentInfo.getPriority(), 1);
    }

    @Test
    void getDueDate() {
        assertEquals(assignmentInfo.getDueDate(), LocalDateTime.of(2022, 12, 2, 4, 38));
    }

    @Test
    void getWeightage() {
        assertEquals(assignmentInfo.getWeightage(), 20.0);
    }

    @Test
    void getTimeNeeded() {
        assertEquals(assignmentInfo.getTimeNeeded(), 0);
    }

    @Test
    void getTimeSpent() {
        assertEquals(assignmentInfo.getTimeSpent(), 0);
    }
}
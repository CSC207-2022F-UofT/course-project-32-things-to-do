package entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {
    static Assignment assignment;

    @BeforeAll
    static void beforeAll() {
        // initialize new assignment
        LocalDateTime date = LocalDateTime.of(2022, 12, 2, 4, 38);
        assignment = new Assignment(
                "a1", "a1_name_course", 1, date, 20.0);
    }

    @Test
    void getId() {
        assertEquals(assignment.getId(), "a1_name_course");
    }

    @Test
    void setId() {
        assignment.setId("a1_name_csc207");
        assertEquals(assignment.getId(), "a1_name_csc207");
    }

    @Test
    void getTitle() {
        assertEquals(assignment.getTitle(), "a1");
    }

    @Test
    void setTitle() {
        assignment.setTitle("just a");
        assertEquals(assignment.getTitle(), "just a");
    }

    @Test
    void getPriority() {
        assertEquals(assignment.getPriority(), 1);
    }

    @Test
    void setPriority() {
        assignment.setPriority(10);
        assertEquals(assignment.getPriority(), 10);
    }

    @Test
    void getComplete() {
        assertFalse(assignment.getComplete());
    }

    @Test
    void setComplete() {
        assignment.setComplete();
        assertTrue(assignment.getComplete());
    }

    @Test
    void getDueDate() {
        assertEquals(assignment.getDueDate(), LocalDateTime.of(2022, 12, 2, 4, 38));
    }

    @Test
    void setDueDate() {
        LocalDateTime newDate = LocalDateTime.of(2022, 11, 10, 13, 30);
        assignment.setDueDate(newDate);
        assertEquals(assignment.getDueDate(), newDate);
    }

    @Test
    void getWeightage() {
        assertEquals(assignment.getWeightage(), 20.0);
    }

    @Test
    void setWeightage() {
        assignment.setWeightage(20.1);
        assertEquals(assignment.getWeightage(), 20.1);
    }

    @Test
    void getGradeReceived() {
        assertEquals(assignment.getGradeReceived(), -1);
    }

    @Test
    void setGradeReceived() {
        assignment.setGradeReceived(100); // my grades frfr
        assertEquals(assignment.getGradeReceived(), 100);
    }

    @Test
    void getTimeSpent() {
        assertEquals(assignment.getTimeSpent(), 0);
    }

    @Test
    void setTimeSpent() {
        assignment.setTimeSpent(5.5);
        assertEquals(assignment.getTimeSpent(), 5.5);
    }

    @Test
    void getTimeNeeded() {
        assertEquals(assignment.getTimeNeeded(), 0);
    }

    @Test
    void setTimeNeeded() {
        assignment.setTimeNeeded(10.0);
        assertEquals(assignment.getTimeNeeded(), 10.0);
    }

    @Test
    void getTimeLeft() {
        double timeLeft = Double.parseDouble(String.valueOf(HOURS.between(LocalDateTime.now(), assignment.getDueDate())));
        assertEquals(assignment.getTimeLeft(), timeLeft);
    }

    @Test
    void schedulePrepTime() {
    }

    @Test
    void getPrepTimeScheduled() {
        assertEquals(0, assignment.getPrepTimeScheduled().size());
    }

    @Test
    void setPrepTimeScheduled() {
        ArrayList<LocalDateTime> time = new ArrayList<>();
        time.add(LocalDateTime.of(2022, 12, 2, 12, 30));
        time.add(LocalDateTime.of(2022, 12, 2, 13, 0));
        ArrayList<ArrayList<LocalDateTime>> prepTimes = new ArrayList<>();
        prepTimes.add(time);

        assignment.setPrepTimeScheduled(prepTimes);
        assertEquals(assignment.getPrepTimeScheduled(), prepTimes);
    }
}
package test_entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.*;
import static org.junit.jupiter.api.Assertions.*;

class TestTest {
    static entities.Test test;
    @BeforeAll
    static void beforeAll() {
        LocalDateTime startTime = LocalDateTime.of(2022, 12, 2, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 12, 2, 13, 0);
        test = new entities.Test("t1", "t1_name_course", 10, startTime, endTime, 30.0);
    }

    @Test
    void getId() {
        assertEquals(test.getId(), "t1_name_course");
    }

    @Test
    void setId() {
        test.setId("just t_name_course");
        assertEquals(test.getId(), "just t_name_course");
    }

    @Test
    void getAndSetTitle() {
        assertEquals(test.getTitle(), "t1");

        test.setTitle("just t");
        assertEquals(test.getTitle(), "just t");
    }

    @Test
    void getAndSetPriority() {
        assertEquals(test.getPriority(), 10);

        test.setPriority(0);
        assertEquals(test.getPriority(), 0);
    }

    @Test
    void getAndSetComplete() {
        assertFalse(test.getComplete());

        test.setComplete();
        assertTrue(test.getComplete());
    }

    @Test
    void getAndSetTimeBlock() {
        assertEquals(test.getTimeBlock()[0], LocalDateTime.of(2022, 12, 2, 12, 0));
        assertEquals(test.getTimeBlock()[1], LocalDateTime.of(2022, 12, 2, 13, 0));

        LocalDateTime newStart = LocalDateTime.of(2022, 12, 3, 12, 0);
        LocalDateTime newEnd = LocalDateTime.of(2022, 12, 3, 13, 0);
        test.setTimeBlock(newStart, newEnd);

        assertEquals(test.getTimeBlock()[0], newStart);
        assertEquals(test.getTimeBlock()[1], newEnd);
    }

    @Test
    void getAndSetWeightage() {
        assertEquals(test.getWeightage(), 30.0);

        test.setWeightage(0);
        assertEquals(test.getWeightage(), 0);
    }

    @Test
    void getAndSetGradeReceived() {
        assertEquals(test.getGradeReceived(), -1);

        test.setGradeReceived(100);
        assertEquals(test.getGradeReceived(), 100);
    }

    @Test
    void getTimeSpent() {
        assertEquals(test.getTimeSpent(), 0);
    }

    @Test
    void setTimeSpent() {
        test.setTimeSpent(2);
        assertEquals(test.getTimeSpent(), 2);
    }

    @Test
    void getTimeNeeded() {
        assertEquals(test.getTimeNeeded(), 0);
    }

    @Test
    void setTimeNeeded() {
        test.setTimeNeeded(10);
        assertEquals(test.getTimeNeeded(), 10);
    }

    @Test
    void getTimeLeft() {
        double timeLeft = Double.parseDouble(String.valueOf(HOURS.between(LocalDateTime.now(), test.getTimeBlock()[0])));
        assertEquals(test.getTimeLeft(), timeLeft);
    }

    @Test
    void getPrepTimeScheduled() {
        assertEquals(test.getPrepTimeScheduled().size(), 1);
    }

    @Test
    void setPrepTimeScheduled() {
        ArrayList<LocalDateTime> time = new ArrayList<>();
        time.add(LocalDateTime.of(2022, 12, 2, 12, 30));
        time.add(LocalDateTime.of(2022, 12, 2, 13, 0));
        ArrayList<ArrayList<LocalDateTime>> prepTimes = new ArrayList<>();
        prepTimes.add(time);

        test.setPrepTimeScheduled(prepTimes);
        assertEquals(test.getPrepTimeScheduled(), prepTimes);
    }
}
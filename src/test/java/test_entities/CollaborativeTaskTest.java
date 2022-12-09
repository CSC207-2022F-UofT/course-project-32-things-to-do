package test_entities;

import entities.CollaborativeTask;
import entities.StudentUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CollaborativeTaskTest {

    static CollaborativeTask collaborativeTask;

    @BeforeAll
    static void beforeAll() {
        // initialize new collaborative task
        LocalDateTime startTime = LocalDateTime.of(2022, 12, 2, 4, 38);
        LocalDateTime endTime = LocalDateTime.of(2022, 12, 4, 4, 38);
        LocalDateTime deadline = LocalDateTime.of(2022, 12, 7, 4, 38);
        StudentUser sima = new StudentUser("sima", "wallywally123");
        collaborativeTask = new CollaborativeTask("ct", "ct_sima_collaborative", 0, false, "", startTime, endTime, deadline, sima);
    }

    @Test
    void getRecurring() {
        assertFalse(collaborativeTask.getRecurring());
    }

    @Test
    void getFrequency() {
        assertEquals(collaborativeTask.getFrequency(), "");
    }

    @Test
    void setRecurringAndFrequency() {
        collaborativeTask.setRecurringAndFrequency(true, "weekly");
        assertTrue(collaborativeTask.getRecurring());
        assertEquals(collaborativeTask.getFrequency(), "weekly");
    }

    @Test
    void getStartTime() {
        LocalDateTime startTime = LocalDateTime.of(2022, 12, 2, 4, 38);
        assertEquals(collaborativeTask.getStartTime(), startTime);
    }

    @Test
    void setStartTime() {
        LocalDateTime newStartTime = LocalDateTime.of(2022, 12, 2, 9, 38);
        collaborativeTask.setStartTime(newStartTime);
        assertEquals(collaborativeTask.getStartTime(), newStartTime);
    }

    @Test
    void getEndTime() {
        LocalDateTime endTime = LocalDateTime.of(2022, 12, 4, 4, 38);
        assertEquals(collaborativeTask.getEndTime(), endTime);
    }

    @Test
    void setEndTime() {
        LocalDateTime newEndTime = LocalDateTime.of(2022, 12, 4, 9, 38);
        collaborativeTask.setEndTime(newEndTime);
        assertEquals(collaborativeTask.getEndTime(), newEndTime);
    }

    @Test
    void setDeadline() {
        LocalDateTime newDeadline = LocalDateTime.of(2022, 12, 7, 9, 38);
        collaborativeTask.setDeadline(newDeadline);
        assertEquals(collaborativeTask.getDeadline(), newDeadline);
    }

    @Test
    void setTimeBlock() {
        LocalDateTime newStartTime = LocalDateTime.of(2022, 12, 2, 9, 38);
        LocalDateTime newEndTime = LocalDateTime.of(2022, 12, 4, 9, 38);
        collaborativeTask.setTimeBlock(newStartTime, newEndTime);
        assertEquals(collaborativeTask.getTimeBlock()[0], newStartTime);
        assertEquals(collaborativeTask.getTimeBlock()[1], newEndTime);
    }

    @Test
    void scheduleTimeBlock() {
    }

    @Test
    void removeTimeBlock() {
    }

    @Test
    void getTimeBlocks() {
    }

    @Test
    void setTimeBlocks() {
    }

    @Test
    void getLeader() {
    }

    @Test
    void setLeader() {
    }

    @Test
    void getTeammates() {
    }

    @Test
    void setTeammates() {
    }

    @Test
    void getPendingTeammates() {
    }

    @Test
    void setPendingTeammates() {
    }

    @Test
    void getDeclinedTeammates() {
    }

    @Test
    void setDeclinedTeammates() {
    }
}
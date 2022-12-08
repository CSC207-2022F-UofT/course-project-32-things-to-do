package test_entities;

import entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapTest {
    static HashMap<String, Task> hashMap;
    static Task assignment, event, test;
    @BeforeAll
    static void beforeAll() {
        assignment = new Assignment("a1", "a1_name_course", 0, LocalDateTime.now(), 20);
        event = new Event("e1", "e1_name_course", 0, LocalDateTime.now(), LocalDateTime.now(), false, "");
        test = new entities.Test("t1", "t1_name_course", 0, LocalDateTime.now(), LocalDateTime.now(), 20);
        hashMap = new HashMap<>();
        hashMap.put("a1_name_course", assignment);
        hashMap.put("e1_name_course", event);
        hashMap.put("t1_name_course", test);
    }
    @Test
    void setAndGetTaskMap() {
        TaskMap.setTaskMap(new HashMap<>());
        assertEquals(TaskMap.getTaskMap(), new HashMap<>());

        TaskMap.setTaskMap(hashMap);
        assertEquals(TaskMap.getTaskMap(), hashMap);
    }

    @Test
    void findTask() {
        // test finding an assignment, event and test
        assertEquals(TaskMap.findTask("a1_name_course"), assignment);
        assertEquals(TaskMap.findTask("e1_name_course"), event);
        assertEquals(TaskMap.findTask("t1_name_course"), test);
    }

    @Test
    void addTask() {
        Assignment assignment2 = new Assignment("a2", "a2_name_course", 0, LocalDateTime.now(), 20);
        Event event2 = new Event("e2", "e2_name_course", 0, LocalDateTime.now(), LocalDateTime.now(), false, "");
        entities.Test test2 = new entities.Test("t2", "t2_name_course", 0, LocalDateTime.now(), LocalDateTime.now(), 20);

        // add new tasks
        TaskMap.addTask("a2_name_course", assignment2);
        TaskMap.addTask("e2_name_course", event2);
        TaskMap.addTask("t2_name_course", test2);

        // check that they exist in the map
        assertEquals(TaskMap.findTask("a2_name_course"), assignment2);
        assertEquals(TaskMap.findTask("e2_name_course"), event2);
        assertEquals(TaskMap.findTask("t2_name_course"), test2);
    }

    @Test
    void removeTask() {
        // remove tasks
        TaskMap.removeTask("a2_name_course");
        TaskMap.removeTask("e2_name_course");
        TaskMap.removeTask("t2_name_course");

        // new map should be the same as before
        assertEquals(TaskMap.getTaskMap(), hashMap);
    }
}
package use_cases.task_management.read_write;

import entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import screens.task_management.FileTaskMap;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileTaskMapTest {
    @BeforeAll
    static void beforeAll() {
        TaskMap.setTaskMap(new HashMap<>());
        TaskMap.addTask("id", new Assignment(
                "title", "id", 0, LocalDateTime.now(), 20));
    }

    @Test
    void saveToFile() {
        FileTaskMap ft = new FileTaskMap("src/main/java/data/TaskMapTest.txt");
        ft.save(TaskMap.getTaskMap());
        assertTrue(ft.existsById("id"));

        TaskMap.addTask("id2", new Assignment(
                "title2", "id2", 0, LocalDateTime.now(), 20));
        ft.save(TaskMap.getTaskMap());
        assertTrue(ft.existsById("id2"));
    }

    @Test
    void readFromFile() {
        FileTaskMap ft = new FileTaskMap("src/main/java/data/TaskMapTest.txt");
        ft.save(TaskMap.getTaskMap());
        HashMap<String, Task> taskMap = ft.load();
        assertTrue(taskMap.containsKey("id"));
        assertTrue(taskMap.containsKey("id2"));

    }
}
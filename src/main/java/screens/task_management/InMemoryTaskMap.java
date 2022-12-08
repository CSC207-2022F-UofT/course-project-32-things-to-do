package screens.task_management;

import entities.*;
import use_cases.task_management.read_write.*;

import java.util.HashMap;

/**
 * TaskMapGateway for testing purposes
 */
public class InMemoryTaskMap implements TaskMapGateway {
    HashMap<String, Task> taskMap = new HashMap<>();

    /**
     * Save the task map
     */
    @Override
    public void save(HashMap<String, Task> taskMap) {
        this.taskMap = taskMap;
    }

    /**
     * "Load" the task map
     * @return - taskMap
     */
    @Override
    public Object load() {
        return taskMap;
    }

    /**
     * Check if an ID exists within the task map
     * @param id - the ID
     * @return - whether the ID exists
     */
    @Override
    public boolean existsById(String id) {
        return taskMap.containsKey(id);
    }
}

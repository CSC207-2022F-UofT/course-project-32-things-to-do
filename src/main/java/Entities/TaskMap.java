package Entities;

import java.io.Serializable;
import java.util.HashMap;

public class TaskMap implements Serializable {
    private static HashMap<String, Task> taskMap;

    /**
     * Find a Task using its unique ID
     * Returns null if the ID does not exist
     *
     * @param id - the ID of the Task
     * @return - the Task corresponding to the ID
     */
    public Task findTask(String id) {
        return taskMap.get(id);
    }

    /**
     * Add a Task to the TaskMap
     * @param id - the ID of the Task
     * @param task - the Task associated with the ID
     * @return - whether the key-value pair has been added successfully
     */
    public boolean addTask(String id, Task task) {
        if (taskMap.containsKey(id)) return false; // 2 Tasks with same ID

        taskMap.put(id, task);
        return true;
    }

    /**
     * Saves the task map to a file
     */
    public void save() {

    }

    /**
     * Loads the task map from a file
     */
    public void load() {

    }
}

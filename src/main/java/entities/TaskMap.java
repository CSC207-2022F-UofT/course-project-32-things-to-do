package entities;

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
    public static Task findTask(String id) {
        return taskMap.get(id);
    }

    /**
     * Add a Task to the TaskMap.txt
     * @param id - the ID of the Task
     * @param task - the Task associated with the ID
     */
    public static void addTask(String id, Task task) {
        taskMap.put(id, task);
    }

    /**
     * Remove a Task from the TaskMap.txt
     * @param id - the ID of the Task being removed
     */
    public static void removeTask(String id) {
        taskMap.remove(id);
    }

    /**
     * Get the full task map
     * @return - the task map
     */
    public static HashMap<String, Task> getTaskMap() {
        return taskMap;
    }

    /**
     * Set the full task map
     * @param tasksMap - the HashMap<String, Task> to set taskMap to
     */
    public static void setTaskMap(HashMap<String, Task> tasksMap) {
        taskMap = tasksMap;
    }

}

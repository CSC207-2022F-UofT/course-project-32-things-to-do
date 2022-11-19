package entities;

import read_write.ReadWriter;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public class TaskMap implements Serializable {
    private static HashMap taskMap;

    /**
     * Find a Task using its unique ID
     * Returns null if the ID does not exist
     *
     * @param id - the ID of the Task
     * @return - the Task corresponding to the ID
     */
    public static Task findTask(String id) {
        return (Task) taskMap.get(id);
    }

    /**
     * Add a Task to the TaskMap
     * @param id - the ID of the Task
     * @param task - the Task associated with the ID
     * @return - whether the key-value pair has been added successfully
     */
    public static boolean addTask(String id, Task task) {
        if (taskMap.containsKey(id)) return false; // 2 Tasks with same ID

        taskMap.put(id, task);
        return true;
    }

    /**
     * Remove a Task from the TaskMap
     * @param task - the Task being removed
     */
    public static void removeTask(Task task) {
        taskMap.remove(task.getId());
    }

    /**
     * Get the full task map
     * @return - the task map
     */
    public static HashMap getTaskMap() {
        return taskMap;
    }

    /**
     * Saves the taskMap to a file
     * @param rw - ReadWriter object saving the taskMap
     */
    public static void saveToFile(ReadWriter rw) {
        try {
            rw.saveToFile("src/Data/TaskMap", taskMap);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Loads the taskMap from a file
     * @param rw - ReadWriter object reading the TaskMap
     */
    public static void load(ReadWriter rw) {
        try {
            taskMap = (HashMap) rw.readFromFile("src/Data/TaskMap");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

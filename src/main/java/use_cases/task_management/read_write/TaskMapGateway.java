package use_cases.task_management.read_write;

import entities.*;

import java.util.HashMap;

public interface TaskMapGateway {
    /**
     * Save a TaskMap to a file
     * @param taskMap - the task map being saved
     */
    void save(HashMap<String, Task> taskMap);

    /**
     * Read in a serializable object from a file
     * @return - the object being read
     */
    Object load();

    /**
     * Check if an Id exists in the task map
     * @param id - the ID
     * @return - whether the ID is in the task map
     */
    boolean existsById(String id);
}

package screens.task_management;

import entities.*;
import use_cases.course_features.course_enrolment_use_case.*;
import use_cases.task_management.read_write.*;

import java.util.HashMap;

/**
 * TaskMapGateway for testing purposes
 */
public class InMemoryTaskMap implements TaskMapGateway, CourseEnrolmentTaskDsGateway {
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

    /**
     * course enrolment use case (for interactor test)
     * @param taskID the unique id of the task
     */
    @Override
    public Task getTask(String taskID) {
        return taskMap.get(taskID);
    }

    /**
     * course enrolment use case (for interactor test)
     * @param newMap the map of the "new" tasks (new key, same value)
     */
    @Override
    public void saveNewMaptoMap(HashMap<String, Task> newMap) {
        taskMap.putAll(newMap);

    }
}

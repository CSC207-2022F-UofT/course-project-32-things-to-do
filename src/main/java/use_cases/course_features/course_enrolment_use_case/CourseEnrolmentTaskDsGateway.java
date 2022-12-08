package use_cases.course_features.course_enrolment_use_case;

import entities.*;

import java.util.HashMap;

/**
 * Gateway containing the following methods (override in FileTaskMap)
 *  NOTE: THIS INVOLVES ONLY METHODS REQUIRING ACCESS TO FILETASKMAP
 *  getTask: gets the Task object corresponding to its id (key)
 *  saveNewMaptoMap: puts all contents of a map into filetaskmap
 */
public interface CourseEnrolmentTaskDsGateway {
    // get task objects with task id
    Task getTask(String taskID);
    // put all key-value pairs of a map to the data file
    void saveNewMaptoMap(HashMap<String, Task> newMap);
}

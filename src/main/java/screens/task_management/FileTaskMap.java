package screens.task_management;

import entities.*;
import use_cases.collaborative_task_scheduling.scheduling_ct_use_case.*;
import use_cases.course_features.course_enrolment_use_case.*;
import use_cases.task_management.read_write.*;

import java.io.*;
import java.util.HashMap;

public class FileTaskMap implements TaskMapGateway, CourseEnrolmentTaskDsGateway, ScheduleCTDSGateway {
    String path;
    HashMap<String, Task> taskMap = new HashMap<>();
    public FileTaskMap(String path) {
        this.path = path;
    }
    /**
     * Save the taskMap to a file
     * @param taskMap - the task map being saved
     */
    public void save(HashMap<String, Task> taskMap) {
        this.taskMap = taskMap;
        try {
            OutputStream file = new FileOutputStream(path);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(taskMap);
            output.flush();
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Read in the taskMap from a file
     * @return - the taskMap being read
     */
    public HashMap<String, Task> load() {
        HashMap<String, Task> taskMap;
        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            taskMap = (HashMap<String, Task>) input.readObject();
            input.close();
            return taskMap;
        } catch(IOException e) {
            System.out.println("File is blank, generating new Task map");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        taskMap = new HashMap<>();
        this.taskMap = taskMap;

        return taskMap;
    }

    @Override
    public boolean existsById(String id) {
        return taskMap.containsKey(id);
    }

    /**
     * For Course enrolment use case
     * returns the task object based on the task id
     * @param taskId the unique id (key) of the task
     * @return task
     */

    /**
     * For course enrolment use case, get a Task based on task id
     * @param taskId - the uniqueid of the task
     */
    @Override
    public Task getTask(String taskId) {
        return taskMap.get(taskId);
    }

    /**
     * For course enrolment use case, added temporary map of new tasks to TaskMap
     * @param newMap the temporary map of 'cloned' course tasks with student username in id
     */
    @Override
    public void saveNewMaptoMap(HashMap<String, Task> newMap) {
        taskMap.putAll(newMap);
        save(taskMap);
    }

    /**
     * For Schedule Collaborative Tasks use case, updates the task map
     * @param taskID - the unique id of the task
     * @param updatedTask - the updated task object
     */
    @Override
    public void updateTaskMap(String taskID, CollaborativeTask updatedTask) {
        taskMap.remove(taskID);
        taskMap.put(updatedTask.getId(), updatedTask);
        save(taskMap);
    }
}

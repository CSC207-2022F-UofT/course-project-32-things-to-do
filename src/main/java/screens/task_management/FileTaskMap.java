package screens.task_management;

import entities.Task;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentTaskDsGateway;
import use_cases.login_registration.user_register_usecase.StudentSaveRequest;
import use_cases.login_registration.user_register_usecase.UserRegSaveRequest;
import use_cases.task_management.read_write.TaskMapGateway;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileTaskMap implements TaskMapGateway, CourseEnrolmentTaskDsGateway {
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
        taskMap = new HashMap<String, Task>();
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
     * @return
     */
    @Override
    public Task getTask(String taskId) {
        return taskMap.get(taskId);
    }

    @Override
    public void saveNewMaptoMap(HashMap<String, Task> newMap) {
        taskMap.putAll(newMap);
        save(taskMap);
    }
}

package use_cases.task_management.read_write;

import entities.Task;

import java.io.*;
import java.util.HashMap;

public class TaskReadWrite implements ReadWriter {
    String path;
    public TaskReadWrite(String path) {
        this.path = path;
    }
    /**
     * Save the taskMap to a file
     * @param taskMap - taskMap being serialized
     */
    public void saveToFile(Object taskMap) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(taskMap);
        output.flush();
        output.close();
    }

    /**
     * Read in the taskMap from a file
     * @return - the taskMap being read
     */
    public HashMap<String, Task> readFromFile() throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(path);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        HashMap<String, Task> taskMap = (HashMap<String, Task>) input.readObject();
        input.close();
        return taskMap;
    }
}

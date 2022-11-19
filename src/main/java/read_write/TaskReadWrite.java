package read_write;

import java.io.*;
import java.util.HashMap;

public class TaskReadWrite implements ReadWriter {
    /**
     * Save the taskMap to a file
     * @param path - filepath location
     * @param taskMap - taskMap being serialized
     */
    public void saveToFile(String path, Object taskMap) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(taskMap);
        output.close();
    }

    /**
     * Read in the taskMap from a file
     * @param path - filepath location
     * @return - the taskMap being read
     */
    public HashMap readFromFile(String path) throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(path);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        HashMap taskMap = (HashMap) input.readObject();
        input.close();
        return taskMap;
    }
}

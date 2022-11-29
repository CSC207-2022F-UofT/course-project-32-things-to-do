<<<<<<<< HEAD:src/main/java/use_cases/task_management/read_write/ReadWriter.java
package use_cases.task_management.read_write;
========
package use_cases.read_write;
>>>>>>>> f84ff17 (at this point im not sure):src/main/java/use_cases/read_write/ReadWriter.java

import java.io.IOException;

public interface ReadWriter {
    /**
     * Save a serializable object to a file
     * @param o - object to be serialized
     */
    void saveToFile(Object o) throws IOException;

    /**
     * Read in a serializable object from a file
     * @return - the object being read
     */
    Object readFromFile() throws IOException, ClassNotFoundException;
}

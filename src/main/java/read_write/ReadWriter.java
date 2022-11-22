package read_write;

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

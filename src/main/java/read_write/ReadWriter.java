package read_write;

import java.io.IOException;

public interface ReadWriter {
    /**
     * Save a serializable object to a file
     * @param path - filepath location
     * @param o - object to be serialized
     */
    void saveToFile(String path, Object o) throws IOException;

    /**
     * Read in a serializable object from a file
     * @param path - filepath location
     * @return - the object being read
     */
    Object readFromFile(String path) throws IOException, ClassNotFoundException;
}

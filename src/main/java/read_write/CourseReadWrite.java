package read_write;

import entities.*;

import java.io.*;
import java.util.ArrayList;

public class CourseReadWrite implements ReadWriter {

    String path;

    public CourseReadWrite(String path) throws IOException, ClassNotFoundException {
        this.path = path;

        CourseMap courses = buildCourseMap();
        saveToFile(courses);

        CourseMap courseMap = readFromFile();
        System.out.println(courseMap);
        System.out.println(courses);
    }

    /**
     * reads in the courseMap from a file (CourseMap.java)
     * @return the courseMap that is read
     */
    public CourseMap readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileReader = new FileInputStream("courses.ser");
        ObjectInputStream in = new ObjectInputStream(fileReader);
        CourseMap courseMap = (CourseMap) in.readObject();
        return courseMap;
    }

    /**
     * saves the courseMap to a file (CourseMap.java)
     * @param courseMap - object to be serialized
     */
    public void saveToFile(Object courseMap) throws IOException {
        FileOutputStream fileWriter = new FileOutputStream("courses.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileWriter);
        out.writeObject(courseMap);
        out.close();
        fileWriter.close();
    }

    /**
     * initialization
     * @return the courseMap
     */
    private static CourseMap buildCourseMap() {
        ArrayList<String> courseTasks = new ArrayList<String>();
        courseTasks.add("task1");
        Course csc207 = new Course("csc207", "paulgries", courseTasks);
        CourseMap courses = new CourseMap();
        courses.addCourse("csc207paulgries", csc207);
        return courses;
    }
}

package screens;

import course_creation_use_case.CourseCreationDsGateway;
import entities.*;
import course_creation_use_case.CourseCreationRequestModel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileCourse implements CourseCreationDsGateway {
    /**
     * is this supposed to be String to Request Model OR String to Course
     */
    private final HashMap<String, CourseCreationRequestModel> courses;
    private final String filePath;

    /**
     * reads the Course database file, stores its contents -- HashMap of course id to Course object
     * @param path -
     */
    public FileCourse(String path) throws IOException, ClassNotFoundException {
        this.courses = readFromFile();
        this.filePath = path;
    }

    /**
     * reads the courseMap from a file
     * @return -
     */
    public HashMap<String, CourseCreationRequestModel> readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileReader = new FileInputStream(this.filePath);
        ObjectInputStream in = new ObjectInputStream(fileReader);
        HashMap<String, CourseCreationRequestModel> courseMap = (HashMap<String, CourseCreationRequestModel>) in.readObject();
        in.close();
        fileReader.close();
        return courseMap;
    }

    /**
     * adds the request model to database
     * @param requestModel the course info that is being saved
     */
    @Override
    public void saveCourse(CourseCreationRequestModel requestModel) {
        courses.put(requestModel.getCourseID(), requestModel);
//        this.saveCourse();
    }
    private void saveCourse() throws IOException {
        FileOutputStream fileWriter = new FileOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(fileWriter);
        out.writeObject(courses);
        out.close();
        fileWriter.close();
    }

    @Override
    public boolean existsByCourseID(String courseIdentifier) {
        return courses.containsKey(courseIdentifier);
    }

    public Map<String, CourseCreationRequestModel> getCourses() {
        return this.courses;
    }
}
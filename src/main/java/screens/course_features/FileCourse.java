package screens.course_features;

import entities.Course;
import use_cases.course_features.course_creation_use_case.CourseCreationDsGateway;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentDsGateway;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileCourse implements CourseCreationDsGateway, CourseEnrolmentDsGateway {
    /**
     * is this supposed to be String to Request Model OR String to Course
     * for clean architecture:
     * private final HashMap<String, CourseCreationSaveRequest> courses;
     */
    private final HashMap<String, Course> courses;
    private final String filePath;

    /**
     * reads the Course database file, stores its contents -- HashMap of course id to SaveRequest object
     * reads the Course database file, stores its contents -- HashMap of course id to Course object
     * @param path  the path to the serializable file that will contain the map of course id to course object
     */
    public FileCourse(String path) throws IOException, ClassNotFoundException {
        this.filePath = path;

        // checks if path or file exists
        if (Files.exists(Path.of(path))) {
            // exists: read existing file, which is the hashmap of all course ids to course objects
            this.courses = readFile();
        } else {
            // dne: create and write empty map to new file
            this.courses = new HashMap<>();
            saveCourse();
        }
    }

    /**
     * reads the file
     * @return the read hashmap
     */
    public HashMap<String, Course> readFile() throws IOException, ClassNotFoundException {
        FileInputStream fileReader = new FileInputStream(this.filePath);
        ObjectInputStream in = new ObjectInputStream(fileReader);
        HashMap<String, Course> f = (HashMap<String, Course>) in.readObject();
        in.close();
        fileReader.close();
        return f;
    }

    /**
     * COURSE CREATION GATEWAY
     * adds the request model to database
     * @param requestModel the course info that is being saved
     */
    @Override
    public void saveCourse(Course requestModel) throws IOException {
        courses.put(requestModel.getCourseID(), requestModel);
        this.saveCourse();
    }

    /**
     * COURSE CREATION GATEWAY
     * writes the map of course ids to course objects into the Course database file
     */
    private void saveCourse() throws IOException {
        FileOutputStream fileWriter = new FileOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(fileWriter);
        out.writeObject(courses);
        out.close();
        fileWriter.close();
    }

    /**
     * COURSE CREATION GATEWAY
     * return whether a course exists with the course identifier
     * @param courseIdentifier the course id to check
     */
    @Override
    public boolean existsByCourseID(String courseIdentifier) {
        return courses.containsKey(courseIdentifier);
    }

    public Map<String, Course> getCourses() {
        return this.courses;
    }

    /**
     * COURSE ENROLMENT GATEWAY
     * adds the student's username to the course's students parameter
     * @param courseID the course id associated to the course the student wants to enrol in
     */
    @Override
    public void saveStudentToCourse(String courseID) {
        courses.get(courseID).getStudents().add("student's username fix");

    }

    /**
     * return whether the student username is already in students parameter
     * return whether the student is already enrolled in the course
     * @param studentIdentifier the student's username
     */
    @Override
    public boolean existsByStudent(String courseID, String studentIdentifier) {
        return courses.get(courseID).getStudents().contains(studentIdentifier);
    }

    @Override
    public ArrayList<String> courseTasks(Course requestModel) {
        return requestModel.getTasks();
    }

//    public void add

    // add tasks to student's to do list
    // sketchy clean architecture happens here
}
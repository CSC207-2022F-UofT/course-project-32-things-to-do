package screens.course_features;

import use_cases.course_features.course_creation_use_case.CourseCreationDsGateway;
import use_cases.course_features.course_creation_use_case.CourseCreationRequestModel;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentDsGateway;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentRequestModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileCourse implements CourseCreationDsGateway, CourseEnrolmentDsGateway {
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
//        this.courses = readFromFile();
        this.filePath = path;

        // check if path / file exists
        // if so, read existing file, if not, create and write empty map to new file
        if (Files.exists(Path.of(path))) {
            this.courses = readFromFile();
        } else {
            this.courses = new HashMap<String, CourseCreationRequestModel>();
            save();
        }
    }

    /**
     * reads the courseMap from a file
     * @return -
     */
    public HashMap<String, CourseCreationRequestModel> readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileReader = new FileInputStream(this.filePath);
        ObjectInputStream in = new ObjectInputStream(fileReader);
        HashMap<String, CourseCreationRequestModel> f = (HashMap<String, CourseCreationRequestModel>) in.readObject();
        in.close();
        fileReader.close();
        return f;
    }

    /**
     * adds the request model to database
     * @param requestModel the course info that is being saved
     */
    @Override
    public void save(CourseCreationRequestModel requestModel) throws IOException {
        courses.put(requestModel.getCourseID(), requestModel);
        this.save();
    }

    /**
     * writes the map of course ids to CourseCreationRequestModel objects into the Course database file
     */
    private void save() throws IOException {
        FileOutputStream fileWriter = new FileOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(fileWriter);
        out.writeObject(courses);
        out.close();
        fileWriter.close();
    }

    /**
     * return whether a course exists with the course identifier
     * @param courseIdentifier the course id to check
     */
    @Override
    public boolean existsByCourseID(String courseIdentifier) {
        return courses.containsKey(courseIdentifier);
    }

    public Map<String, CourseCreationRequestModel> getCourses() {
        return this.courses;
    }

    @Override
    public boolean existsByStudent(String studentIdentifier) {
        return courses.containsKey(studentIdentifier);
    }

    public void save(CourseEnrolmentRequestModel requestModel) {
    }

}
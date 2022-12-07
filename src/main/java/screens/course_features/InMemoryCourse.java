package screens.course_features;

// not needed for functionality, only for testing

import entities.Course;
import use_cases.course_features.course_creation_use_case.CourseCreationDsGateway;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentCourseDsGateway;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * gateway for testing purposes
 */
public class InMemoryCourse implements CourseCreationDsGateway, CourseEnrolmentCourseDsGateway {
    private Map<String, Course> courses = new HashMap<>();

    public InMemoryCourse() {
        this.courses = new HashMap<>();
    }

    public InMemoryCourse(Map<String, Course> courses) {
        this.courses = courses;
    }

    // populate

    /**
     * Creation + Enrolment use case
     * @param identifier the course's course id
     * @return whether the course exists
     */
    @Override
    public boolean existsByCourseID(String identifier) {
        return courses.containsKey(identifier);
    }

    public Map<String, Course> getCourses() {
        return this.courses;
    }

    /**
     * enrolment use case
     * @param courseIdentifier -
     */
    @Override
    public Course searchForCourse(String courseIdentifier) {
        return courses.get(courseIdentifier);
    }

    /**
     * enrolment use case
     * @param courseID -
     * @param studentIdentifier -
     */
    @Override
    public boolean existsStudentInCourse(String courseID, String studentIdentifier) {
        return courses.get(courseID).getStudents().contains(studentIdentifier);
    }

    @Override
    public void saveStudentToCourse(String studentID, String courseID) throws IOException {
        courses.get(courseID).getStudents().add(studentID);
    }

    @Override
    public ArrayList<String> getCourseTasks(String courseID) {
        return courses.get(courseID).getTasks();
    }

    /**
     * @param requestModel the data to save
     */
    @Override
    public void save(Course requestModel) {
        courses.put(requestModel.getCourseID(), requestModel);
    }
}

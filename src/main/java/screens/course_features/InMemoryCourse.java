package screens.course_features;

// not needed for functionality, only for testing

import entities.Course;
import use_cases.course_features.course_creation_use_case.CourseCreationDsGateway;
import use_cases.course_features.course_creation_use_case.CourseCreationRequestModel;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentDsGateway;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryCourse implements CourseCreationDsGateway, CourseEnrolmentDsGateway {
    private final Map<String, Course> courses = new HashMap<>();

    // populate

    /**
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
    @Override
    public Course searchForCourse(String courseIdentifier) {
        return courses.get(courseIdentifier);
    }

    @Override
    public boolean existsStudentInCourse(String courseID, String studentIdentifier) {
        return courses.get(courseID).getStudents().contains(studentIdentifier);
    }

    @Override
    public void saveStudentToCourse(String studentID, String courseID) throws IOException {
        courses.get(courseID).getStudents().add(studentID);
    }

    @Override
    public ArrayList<String> courseTasks(Course requestModel) {
        return requestModel.getTasks();
    }

    /**
     * @param requestModel the data to save
     */
    @Override
    public void saveCourse(Course requestModel) {
        System.out.println("Save " + requestModel.getCourseID());
    }
}

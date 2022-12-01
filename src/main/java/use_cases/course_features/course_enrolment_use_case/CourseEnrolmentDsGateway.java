package use_cases.course_features.course_enrolment_use_case;

// Use case layer

import entities.Course;
import java.util.ArrayList;

/**
 * Gateway containing the following methods (overriden in FileCourse)
 * saveStudentToCourse: takes student username and appends it to the Course's 'students' parameter
 * existsByStudent: checks if student username exists in the course's 'students' parameter
 * courseTasks: the course's 'tasks' parameter; a list of task id strings
 */
public interface CourseEnrolmentDsGateway {
    // checks if student is in the course through the students argument of the Course
    // object (value) from the course id (key)
    void saveStudentToCourse(String courseID);
    boolean existsByStudent(String courseID, String studentIdentifier);
    public ArrayList<String> courseTasks(Course requestModel);

}

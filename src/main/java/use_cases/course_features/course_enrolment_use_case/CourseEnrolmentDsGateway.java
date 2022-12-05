package use_cases.course_features.course_enrolment_use_case;

// Use case layer

import entities.Course;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Gateway containing the following methods (override in FileCourse)
 * NOTE: THIS INVOLVES ONLY METHODS REQUIRING ACCESS TO FILECOURSE
 * existsByCourseID: need to check if the course student wants to enrol in actually exists
 * existsByStudent: checks if student username exists in the course's 'students' parameter
 * searchForCourse: find the course object associated with the course id if it exists
 * saveStudentToCourse: takes student username and appends it to the Course's 'students' parameter
 * courseTasks: the course's 'tasks' parameter; a list of task id strings
 */
public interface CourseEnrolmentDsGateway {
    boolean existsByCourseID(String courseIdentifier); // exact same name as CourseCreationDsGateway
    public Course searchForCourse(String courseIdentifier);
    boolean existsStudentInCourse(String courseID, String studentIdentifier);
    void saveStudentToCourse(String studentID, String courseID) throws IOException;
    public ArrayList<String> courseTasks(Course requestModel);

}

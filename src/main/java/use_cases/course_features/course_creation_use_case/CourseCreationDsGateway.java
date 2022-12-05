package use_cases.course_features.course_creation_use_case;

// Use case layer

import entities.Course;

import java.io.IOException;


/**
 * Gateway containing the following methods (override in FileCourse)
 * existsByCourseID: takes course id and checks whether that is a key in the database's hashmap
 * saveCourse: takes course id and created course object, and adds it to the course hashmap database
 */
public interface CourseCreationDsGateway {
    boolean existsByCourseID(String courseIdentifier);

    void saveCourse(Course requestModel) throws IOException;
}

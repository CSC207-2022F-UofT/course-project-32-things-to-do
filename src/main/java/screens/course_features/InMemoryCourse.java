package screens.course_features;

// not needed for functionality, only for testing

import entities.Course;
import use_cases.course_features.course_creation_use_case.CourseCreationDsGateway;
import use_cases.course_features.course_creation_use_case.CourseCreationRequestModel;


import java.util.HashMap;
import java.util.Map;

public class InMemoryCourse implements CourseCreationDsGateway {
    private final Map<String, CourseCreationRequestModel> courses = new HashMap<>();

    // populate

    /**
     * @param identifier the course's course id
     * @return whether the course exists
     */
    @Override
    public boolean existsByCourseID(String identifier) {
        return courses.containsKey(identifier);
    }

    /**
     * @param requestModel the data to save
     */
    @Override
    public void saveCourse(Course requestModel) {
        System.out.println("Save " + requestModel.getCourseID());
    }
}

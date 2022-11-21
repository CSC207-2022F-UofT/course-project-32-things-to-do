package screens;

import course_creation_use_case.CourseCreationDsGateway;
import course_creation_use_case.CourseCreationRequestModel;


import java.util.HashMap;
import java.util.Map;

public class InMemoryCourse implements CourseCreationDsGateway {
    final private Map<String, CourseCreationRequestModel> courses = new HashMap<>();

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
    public void saveCourse(CourseCreationRequestModel requestModel) {
        System.out.println("Save " + requestModel.getCourseID());
    }
}

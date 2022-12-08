package screens.course_features;

import use_cases.course_features.course_creation_use_case.*;

import java.util.ArrayList;

public class CourseCreationController {
    final CourseCreationInputBoundary courseInput;
    public CourseCreationController(CourseCreationInputBoundary courseGateway) {
        this.courseInput = courseGateway;
    }

    void create(String courseName, String courseInstructor,
                ArrayList<String> tasks) {
        CourseCreationRequestModel requestModel = new CourseCreationRequestModel(
                courseName, courseInstructor, tasks);

        courseInput.create(requestModel);
    }
}
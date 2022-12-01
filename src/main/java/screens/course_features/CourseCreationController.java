package screens.course_features;

import use_cases.course_features.course_creation_use_case.CourseCreationInputBoundary;
import use_cases.course_features.course_creation_use_case.CourseCreationRequestModel;
import use_cases.course_features.course_creation_use_case.CourseCreationResponseModel;

import java.util.ArrayList;

public class CourseCreationController {
    final CourseCreationInputBoundary courseInput;
    public CourseCreationController(CourseCreationInputBoundary courseGateway) {
        this.courseInput = courseGateway;
    }

    CourseCreationResponseModel create(String courseName, String courseInstructor,
                                       ArrayList<String> tasks) {
        CourseCreationRequestModel requestModel = new CourseCreationRequestModel(
                courseName, courseInstructor, tasks);

        return courseInput.create(requestModel);
    }

}
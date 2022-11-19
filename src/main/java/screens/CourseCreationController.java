package screens;

import course_creation_use_case.CourseCreationInputBoundary;
import course_creation_use_case.CourseCreationRequestModel;
import course_creation_use_case.CourseCreationResponseModel;

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
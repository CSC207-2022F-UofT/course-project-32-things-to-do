package screens;

import course_creation_use_case.courseCreationInputBoundary;
import course_creation_use_case.courseCreationRequestModel;
import course_creation_use_case.courseCreationResponseModel;

import java.util.ArrayList;

public class CourseCreationController {
    final courseCreationInputBoundary courseInput;
    public CourseCreationController(courseCreationInputBoundary courseGateway) {
        this.courseInput = courseGateway;
    }

    courseCreationResponseModel create(String courseName, String courseInstructor,
                                       ArrayList<String> tasks) {
        courseCreationRequestModel requestModel = new courseCreationRequestModel(
                courseName, courseInstructor, tasks);

        return courseInput.create(requestModel);
    }

}
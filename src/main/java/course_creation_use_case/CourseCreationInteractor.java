package course_creation_use_case;

// Use case layer

import entities.*;

public class CourseCreationInteractor implements CourseCreationInputBoundary {
    final CourseCreationDsGateway courseCreationDSGateway;
    final CourseCreationPresenter courseCreationPresenter;
    final CourseMap courseMap;

    public CourseCreationInteractor(CourseCreationDsGateway courseCreationDSGateway, CourseCreationPresenter courseCreationPresenter,
                                    CourseMap courseMap) {
        this.courseCreationDSGateway = courseCreationDSGateway;
        this.courseCreationPresenter = courseCreationPresenter;
        this.courseMap = courseMap;
    }

    @Override
    public CourseCreationResponseModel create(CourseCreationRequestModel requestModel) {
        /* At least one info box left blank */
        if (requestModel.getCourseName().equals("") || requestModel.getCourseInstructor().equals("") || requestModel.getTasks().isEmpty()) {
            return courseCreationPresenter.prepareFailView("Please fill in all required information.");
        }
        /*
        Note: Jonathan - no need to check if is instructor; users would have different
        views because they are in different use cases
        If the course id (same course name and instructor name) already exists, new
        course will not be made.
        Else success
         */
        if (courseCreationDSGateway.existsByCourseID(requestModel.getCourseID())) {
            return courseCreationPresenter.prepareFailView("Course already exists.");
        }

        /* create new course */
        Course course = new Course(requestModel.getCourseName(), requestModel.getCourseInstructor(), requestModel.getTasks());
        CourseMap.addCourse(requestModel.getCourseID(), course);

        /* checks passed, course successfully created and saved */
        CourseCreationRequestModel courseCreationModel = new CourseCreationRequestModel(course.getCourseName(), course.getCourseInstructor(), course.getTasks());
        courseCreationDSGateway.saveCourse(courseCreationModel);

        /* checks passed, course sent to presenter */
        CourseCreationResponseModel courseResponseModel = new CourseCreationResponseModel(
                course.getCourseID(), course.getTasks());
        return courseCreationPresenter.prepareSuccessView(courseResponseModel);

    }
}

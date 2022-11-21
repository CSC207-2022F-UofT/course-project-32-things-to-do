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

    /**
     * Creates the task in the request model and returns the corresponding response model
     * @param requestModel the input from the instructor
     */
    @Override
    public CourseCreationResponseModel create(CourseCreationRequestModel requestModel) {

        // At least one field left blank
        if (requestModel.getCourseName().equals("") || requestModel.getCourseInstructor().equals("") || requestModel.getTasks().isEmpty()) {
            return courseCreationPresenter.prepareFailView("Please fill in all required information.");
        }

        // Note: Jonathan - no need to check the type of User, students and instructors
        // would have different views because they are in different use cases
        // checks whether the course id is already in the CourseMap (course already exists)
        if (courseCreationDSGateway.existsByCourseID(requestModel.getCourseID())) {
            return courseCreationPresenter.prepareFailView("Course already exists.");
        }

        // checks passed; course can be created

        // create a new course
        Course course = new Course(requestModel.getCourseName(), requestModel.getCourseInstructor(), requestModel.getTasks());
        CourseMap.addCourse(requestModel.getCourseID(), course);

        // course successfully created and saved
        CourseCreationRequestModel courseCreationModel = new CourseCreationRequestModel(course.getCourseName(), course.getCourseInstructor(), course.getTasks());
        courseCreationDSGateway.saveCourse(courseCreationModel);

        // course sent to presenter
        CourseCreationResponseModel courseResponseModel = new CourseCreationResponseModel(
                course.getCourseID(), course.getTasks());
        return courseCreationPresenter.prepareSuccessView(courseResponseModel);
    }
}

package course_enrolment_and_creation_usecase;

import Entities.Course;

// Use case layer

/* Notes
VERY CONFUSED --> NEED COURSE FACTORY?
** the class that runs the use case (subclass of input boundary)
the highest level concept; the most protected
 */

public class courseCreationInteractor implements courseCreationInputBoundary {
    final courseCreationDSGateway courseCreationDSGateway;
    final courseCreationPresenter courseCreationPresenter;

    public courseCreationInteractor(courseCreationDSGateway courseCreationDSGateway, courseCreationPresenter courseCreationPresenter) {
        this.courseCreationDSGateway = courseCreationDSGateway;
        this.courseCreationPresenter = courseCreationPresenter;
    }

    @Override
    public courseCreationResponseModel create(courseCreationRequestModel requestModel) {
        /* At least one info box left blank */
        if (requestModel.getCourseName().equals("") || requestModel.getCourseInstructor().equals("") || requestModel.getTasks().isEmpty()) {
            return courseCreationPresenter.prepareFailView("Please fill in all required information.");
        }
        /*
        Note: no need to check if is instructor; users would have different
        views because they are in different use cases
        If the course id (same course name and instructor name) already exists, new
        course will not be made.
        Else success
         */
        if (courseCreationDSGateway.existsByCourseID(requestModel.getCourseID())) {
            return courseCreationPresenter.prepareFailView("Course already exists.");
        }
        return null;

        /*
         * what is a courseFactory and is it needed
         */

//        Course course = courseFactory.create(requestModel.getCourseID(), requestModel.getTasks());
//
//        /* checks passed, course successfully created and saved */
//        courseCreationDSRequestModel courseCreationDSModel = new courseCreationDSRequestModel(course.getCourseName(), course.getCourseInstructor(), course.getTasks());
//        courseCreationDSGateway.saveCourse(courseCreationDSModel);
//
//        /* checks passed, course sent to presenter */
//        courseCreationResponseModel courseResponseModel = new courseCreationResponseModel(
//                                                            course.getCourseID(), course.getTasks());
//        return courseCreationPresenter.prepareFailViewSuccessView(courseResponseModel);
    }
}

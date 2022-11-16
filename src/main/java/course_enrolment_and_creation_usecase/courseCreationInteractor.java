package course_enrolment_and_creation_usecase;

import Entities.Course;

// Use case layer

// Notes
// VERY CONFUSED --> NEED COURSE FACTORY?
// ** the class that runs the use case (subclass of input boundary)
// the highest level concept; the most protected

public class courseCreationInteractor implements courseCreationInputBoundary {
    final courseCreationDSGateway courseCreationDSGateway;
    final courseCreationPresenter courseCreationPresenter;

    public courseCreationInteractor(courseCreationDSGateway courseCreationDSGateway,
                                    courseCreationPresenter courseCreationPresenter) {
        this.courseCreationDSGateway = courseCreationDSGateway;
        this.courseCreationPresenter = courseCreationPresenter;
    }

    @Override
    public courseCreationResponseModel create(courseCreationRequestModel requestModel) {
        /*
        If the course id (same course name and instructor name) already exists, new
        course will not be made.
        Otherwise pass
         */
        if (courseCreationDSGateway.existsByCourseID(requestModel.getCourseID())) {
            return courseCreationPresenter.prepareFailView("Course already exists.");
        }

//        Course course = courseFactory.create(requestModel.getCourseID(), requestModel.getTasks());
//        courseCreationDSRequestModel courseCreationDSModel
//                = new courseCreationDSRequestModel(course.ge)
        /*
        blah blah blah saved to gateway huh
        make new ___ in response model
        then return success view
         */
        return null;
    }
}

package course_enrolment_and_creation_usecase;

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
        fail: instructor and course match with one in database
        success otherwise
         */
        return null;
    }
}

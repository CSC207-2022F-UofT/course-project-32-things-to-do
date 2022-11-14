package course_enrolment_and_creation_usecase;

// Use case layer

public interface courseCreationPresenter {
    courseCreationResponseModel prepareSuccessView(courseCreationResponseModel newCourse);

    courseCreationResponseModel prepareFailView(courseCreationResponseModel error);
}

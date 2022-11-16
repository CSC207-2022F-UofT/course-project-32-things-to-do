package course_enrolment_and_creation_usecase;

// Use case layer

// Notes:
// DONE
// ** the public interface for calling the use case
// boundaries are interfaces
// input boundary is between the controller and use case

public interface courseCreationInputBoundary {
    courseCreationResponseModel create(courseCreationRequestModel requestModel);
}

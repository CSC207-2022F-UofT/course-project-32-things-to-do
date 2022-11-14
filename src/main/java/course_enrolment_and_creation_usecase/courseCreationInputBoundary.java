package course_enrolment_and_creation_usecase;

import Entities.Course;

public interface courseCreationInputBoundary {
    courseCreationResponseModel create(courseCreationRequestModel requestModel);
}

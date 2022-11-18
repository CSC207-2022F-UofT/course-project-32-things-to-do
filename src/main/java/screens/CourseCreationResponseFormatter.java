package screens;

// Interface adapters layer

import course_creation_use_case.courseCreationPresenter;
import course_creation_use_case.courseCreationResponseModel;

public class CourseCreationResponseFormatter implements courseCreationPresenter {

    @Override
    public courseCreationResponseModel prepareSuccessView(courseCreationResponseModel response) {
        /* not sure what this is lol */
        return response;
    }

    @Override
    public courseCreationResponseModel prepareFailView(String error) {
        throw new CourseCreationFailed(error);
    }
}

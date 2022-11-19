package screens;

// Interface adapters layer

import course_creation_use_case.CourseCreationPresenter;
import course_creation_use_case.CourseCreationResponseModel;

public class CourseCreationResponseFormatter implements CourseCreationPresenter {

    @Override
    public CourseCreationResponseModel prepareSuccessView(CourseCreationResponseModel response) {
        /* not sure what this is lol */
        return response;
    }

    @Override
    public CourseCreationResponseModel prepareFailView(String error) {
        throw new CourseCreationFailed(error);
    }
}

package course_enrolment_use_case;

// Use case layer

/*
* Done?
* */

public interface CourseEnrolmentPresenter {
    /* when course is found */
    CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel newStudent);

    /* when course name and instructor combo not present in database*/
    CourseEnrolmentResponseModel prepareFailView(String error);
}

package course_enrolment_use_case;

// Use case layer

/*
* Done?
* */

public interface courseEnrolmentPresenter {
    /* when course is found */
    courseEnrolmentResponseModel prepareSuccessView(courseEnrolmentResponseModel newStudent);

    /* when course name and instructor combo not present in database*/
    courseEnrolmentResponseModel prepareFailView(String error);
}

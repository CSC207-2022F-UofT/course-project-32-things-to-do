package course_enrolment_use_case;

// Use case layer

public class CourseEnrolmentInteractor implements CourseEnrolmentInputBoundary {
    final CourseEnrolmentDsGateway courseEnrolmentDsGateway;
    final CourseEnrolmentPresenter courseEnrolmentPresenter;

    public CourseEnrolmentInteractor(CourseEnrolmentDsGateway courseEnrolmentDsGateway, CourseEnrolmentPresenter courseEnrolmentPresenter) {
        this.courseEnrolmentDsGateway = courseEnrolmentDsGateway;
        this.courseEnrolmentPresenter = courseEnrolmentPresenter;
    }

    @Override
    public CourseEnrolmentResponseModel create(CourseEnrolmentRequestModel requestModel) {
        /* At least one info box left blank */
        if (requestModel.getCourseName().equals("") || requestModel.getCourseInstructor().equals("")) {
            return courseEnrolmentPresenter.prepareFailView("Please fill in all required information." );
        /* map is courseid --> Course
        * checks if given course id is in the map of existing courses*/
        }
//        else if (!CourseMap.containsKey(requestModel.getCourseID())) {
//            return courseEnrolmentPresenter.prepareFailView("Entered information does not correspond to an existing course.");
//        }

        /*
        check whether student is enrolled in course
        map is courseid --> Course objects
        with valid courseid, go to Course.students and check if it contains STUDENT's id
         */
//        if (CourseMap.value(requestModel.getCourseID()).contains(courseEnrolmentDsGateway.existsByStudent(requestModel.getStudentID()))) {
//            return courseEnrolmentPresenter.prepareFailView("Already enrolled in course.");
//        }

    return null;
    }

}

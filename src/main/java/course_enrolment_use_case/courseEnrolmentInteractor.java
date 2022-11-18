package course_enrolment_use_case;

// Use case layer

import Entities.CourseMap;

public class courseEnrolmentInteractor implements courseEnrolmentInputBoundary {
    final courseEnrolmentDsGateway courseEnrolmentDsGateway;
    final courseEnrolmentPresenter courseEnrolmentPresenter;

    public courseEnrolmentInteractor(courseEnrolmentDsGateway courseEnrolmentDsGateway, courseEnrolmentPresenter courseEnrolmentPresenter) {
        this.courseEnrolmentDsGateway = courseEnrolmentDsGateway;
        this.courseEnrolmentPresenter = courseEnrolmentPresenter;
    }

    @Override
    public courseEnrolmentResponseModel create(courseEnrolmentRequestModel requestModel) {
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

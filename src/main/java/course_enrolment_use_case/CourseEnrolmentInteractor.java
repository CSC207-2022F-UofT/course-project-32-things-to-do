package course_enrolment_use_case;


// Use case layer

import Entities.Course;
import Entities.CourseMap;
import course_creation_use_case.CourseCreationRequestModel;
import course_creation_use_case.CourseCreationResponseModel;

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
//        else if (!CourseMap.addCourse(requestModel.getCourseID())) {
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

        /*
        add student id to courses' list of students
        update + save coursemap edits
        success view to presenter
         */

    return null;
    }
}

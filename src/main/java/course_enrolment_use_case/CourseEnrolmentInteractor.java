package course_enrolment_use_case;


// Use case layer

import entities.*;

public class CourseEnrolmentInteractor implements CourseEnrolmentInputBoundary {
    final CourseEnrolmentDsGateway courseEnrolmentDsGateway;
    final CourseEnrolmentOutputBoundary courseEnrolmentOutputBoundary;
    final CourseMap courseMap;
    final String studentID;

    public CourseEnrolmentInteractor(CourseEnrolmentDsGateway courseEnrolmentDsGateway, CourseEnrolmentOutputBoundary courseEnrolmentOutputBoundary,
                                     CourseMap courseMap, String studentID) {
        this.courseEnrolmentDsGateway = courseEnrolmentDsGateway;
        this.courseEnrolmentOutputBoundary = courseEnrolmentOutputBoundary;
        this.courseMap = courseMap;
        this.studentID = studentID;
    }

    /**
     * Executes task in the request model and returns the corresponding response model
     * @param requestModel the input from the student user
     */
    @Override
    public CourseEnrolmentResponseModel create(CourseEnrolmentRequestModel requestModel) {

        // At least one field left blank
        if (requestModel.getCourseName().equals("") || requestModel.getCourseInstructor().equals("")
        || requestModel.getStudentID().equals("")) {
            return courseEnrolmentOutputBoundary.prepareFailView("Please fill in all required information." );
        }

        // checks if given course id is in the map of existing courses
        if (CourseMap.findCourse(requestModel.getCourseID()) == null) {
            return courseEnrolmentOutputBoundary.prepareFailView("Entered information does not correspond to an existing course.");
        }

        // checks whether the student is already enrolled in the course
        // CourseMap is courseid : Course object
        // with course id, find its corresponding Course value, go to its students parameter, and search through that :)

        // to do
//        if (CourseMap.findCourse(requestModel.getCourseID()).)
//        if (CourseMap.value(requestModel.getCourseID()).contains(courseEnrolmentDsGateway.existsByStudent(requestModel.getStudentID()))) {
//            return courseEnrolmentPresenter.prepareFailView("Already enrolled in course.");
//        }

        // checks passed; student id can be added to course's students parameter

        // get the student's id (their username)
//        String enrolledStudent = requestModel.getStudentID();
        // to do: add the student id to the associated courses' task parameter
        // .getStudentId.add.......

        // course edits updated
        // to do: need a 'updateCourse' method?

        // sent to presenter
        // to do: fix this
//        CourseEnrolmentResponseModel enrolmentResponseModel =
//                new CourseEnrolmentResponseModel(enrolledStudent.toLowerCase());
//        return courseEnrolmentOutputBoundary.prepareSuccessView(enrolmentResponseModel);
    return null;
    }
}

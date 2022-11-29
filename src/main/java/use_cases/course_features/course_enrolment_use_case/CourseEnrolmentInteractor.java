package use_cases.course_features.course_enrolment_use_case;


// Use case layer

import entities.*;

import java.util.ArrayList;

public class CourseEnrolmentInteractor implements CourseEnrolmentInputBoundary {
    final CourseEnrolmentOutputBoundary courseEnrolmentOutputBoundary;
    final CourseMap courseMap;
    final String studentID;

    public CourseEnrolmentInteractor(CourseEnrolmentOutputBoundary courseEnrolmentOutputBoundary,
                                     CourseMap courseMap, String studentID) {
        this.courseEnrolmentOutputBoundary = courseEnrolmentOutputBoundary;
        this.courseMap = courseMap;
        this.studentID = studentID;
    }

    /**
     * Executes task in the request model and returns the corresponding response model
     * @param requestModel the input from the student user
     */
    @Override
    public CourseEnrolmentResponseModel enrol(CourseEnrolmentRequestModel requestModel) {

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

        // checks whether the student is already enrolled in the course
        // gets the arraylist of all students in the course, then checks whether the student id is in it
        ArrayList<String> courseStudents = CourseMap.findCourse(requestModel.getCourseID()).getStudents();
        if (courseStudents.contains(requestModel.getStudentID())) {
            return courseEnrolmentOutputBoundary.prepareFailView("Already enrolled in course");
        }

        // checks passed; student can be enrolled + course tasks 'cloned' (hopefully?)

        // add student id to Course parameter 'students'
        CourseMap.findCourse(requestModel.getCourseID()).addStudent(requestModel.getStudentID());

        // get course's tasks
        ArrayList<String> courseTasks = CourseMap.findCourse(requestModel.getCourseID()).getTasks();

        // get the user object for the user in front of the computer
        /** not sure how i am able to initialize the student / user at the screen
         * wouldn't make sense to initialize by creating a new StudentUser because i shouldn't be able to
         * 'get' the password?**/
        StudentUser user = new StudentUser(requestModel.getStudentID(),"helpwhatispassword");
        // append each task array to student user's task list
        for (String task : courseTasks ) {
            user.getToDoList().add(task);
        }
        /** don't think anything in this chunk is needed:
        // tasks successfully added and saved
        CourseEnrolmentRequestModel courseEnrolmentModel = new CourseEnrolmentRequestModel(user.get);
        // do I even need to save anything
        courseEnrolmentDsGateway.saveStudent(courseEnrolmentModel);
         **/

        // sent to presenter
        CourseEnrolmentResponseModel enrolmentResponseModel = new CourseEnrolmentResponseModel(
                user.getName());
        return courseEnrolmentOutputBoundary.prepareSuccessView(enrolmentResponseModel);
    }
}

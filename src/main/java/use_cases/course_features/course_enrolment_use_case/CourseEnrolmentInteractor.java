package use_cases.course_features.course_enrolment_use_case;

// Use case layer

import entities.*;

import java.io.IOException;
import java.util.ArrayList;

public class CourseEnrolmentInteractor implements CourseEnrolmentInputBoundary {

    final CourseEnrolmentDsGateway courseEnrolmentDsGateway;
    final CourseEnrolmentOutputBoundary courseEnrolmentOutputBoundary;
    private StudentUser student; // for response model
    private Course enrolledCourse; // for response model

    public CourseEnrolmentInteractor(CourseEnrolmentDsGateway courseEnrolmentDsGateway,
                                     CourseEnrolmentOutputBoundary courseEnrolmentOutputBoundary) {
        this.courseEnrolmentDsGateway = courseEnrolmentDsGateway;
        this.courseEnrolmentOutputBoundary = courseEnrolmentOutputBoundary;
    }

    /**
     * Creates the task in the request model and returns the corresponding response model
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
        if (courseEnrolmentDsGateway.existsByCourseID(requestModel.getCourseID())) {
            return courseEnrolmentOutputBoundary.prepareFailView("Entered information does not correspond to an existing course.");
        }

        // checks whether the student is already enrolled in the course
        if (courseEnrolmentDsGateway.existsStudentInCourse(requestModel.getCourseID(), requestModel.getStudentID())) {
            return courseEnrolmentOutputBoundary.prepareFailView("Already enrolled in course.");
        }

        // checks passed; student can be enrolled + alias of course's tasks created
        // no need to import FileCourse because it implements the gateway

        // add student id to Course parameter 'students'
        // both lines should do the same thing
        courseEnrolmentDsGateway.searchForCourse(requestModel.getCourseID()).getStudents().add(requestModel.getStudentID());
        try {
            courseEnrolmentDsGateway.saveStudentToCourse(requestModel.getStudentID(), requestModel.getCourseID());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // get course's tasks by creating an alias of the Courses tasks parameter (needs to be referring to the same tasks)
        ArrayList<String> courseTasks = courseEnrolmentDsGateway.courseTasks(courseEnrolmentDsGateway.searchForCourse(requestModel.getCourseID()));
        ArrayList<String> courseTasksCopy = courseTasks;

        // editing the id (key) of each task
        // toss to task creation request model

        // create response model, sent to presenter
        CourseEnrolmentResponseModel enrolmentResponseModel = new CourseEnrolmentResponseModel(
                student.getName(), enrolledCourse.getCourseID(), courseTasksCopy);

        return courseEnrolmentOutputBoundary.prepareSuccessView(enrolmentResponseModel);
    }
}

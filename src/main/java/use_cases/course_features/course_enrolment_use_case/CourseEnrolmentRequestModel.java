package use_cases.course_features.course_enrolment_use_case;

// Use case layer

public class CourseEnrolmentRequestModel {
    private final String courseName;
    private final String courseInstructor;
    private final String courseID;
    private final String studentID;
//    private ArrayList<String> tasks;

    /**
     * Creates a request model with the given input
     * @param courseName the name of the course
     * @param courseInstructor the instructor of the course
     * @param studentID the student user's username (unique id)
     */
    public CourseEnrolmentRequestModel(String courseName, String courseInstructor,
                                       String studentID) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.courseID = courseName + courseInstructor;
        this.studentID = studentID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }


    public String getCourseID() {
        return courseID;
    }

    public String getStudentID() {
        return studentID;
    }
}

package course_enrolment_use_case;

// Use case layer

public class CourseEnrolmentRequestModel {
    private String courseName;
    private String courseInstructor;
    private final String courseID;
    private final String studentID;

    public CourseEnrolmentRequestModel(String courseName, String courseInstructor,
                                       String studentID) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.courseID = courseName + courseInstructor;
        this.studentID = studentID;
    }

    /**
     * Change getters to public?
     */
    String getCourseName() {
        return courseName;
    }

    public void setCourseName() {
        this.courseName = courseName;
    }

    String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor() {
        this.courseInstructor = courseInstructor;
    }

    String getCourseID() {
        return courseID;
    }

    String getStudentID() {
        return studentID;
    }
}

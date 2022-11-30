package use_cases.course_features.course_enrolment_use_case;

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
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName() {
        this.courseName = courseName;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor() {
        this.courseInstructor = courseInstructor;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getStudentID() {
        return studentID;
    }
}

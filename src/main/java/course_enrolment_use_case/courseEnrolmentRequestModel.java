package course_enrolment_use_case;

// Use case layer

public class courseEnrolmentRequestModel {
    private String courseName;
    private String courseInstructor;
    private final String courseID;

    public courseEnrolmentRequestModel(String courseName, String courseInstructor) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.courseID = courseName + courseInstructor;
    }

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
}

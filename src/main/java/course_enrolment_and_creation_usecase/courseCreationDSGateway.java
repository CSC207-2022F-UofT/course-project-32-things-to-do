package course_enrolment_and_creation_usecase;

public interface courseCreationDSGateway {
    boolean existsByCourseName(String courseIdentifier);
    boolean existsByInstructorName(String instructorIdentifier);

    void saveCourse(courseCreationRequestModel requestModel);
}

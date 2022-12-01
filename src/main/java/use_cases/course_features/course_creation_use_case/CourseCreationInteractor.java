package use_cases.course_features.course_creation_use_case;

// Use case layer

import entities.*;

import java.io.IOException;

public class CourseCreationInteractor implements CourseCreationInputBoundary {
    final CourseCreationDsGateway courseCreationDSGateway;
    // this is called in filecourse, where stuff is added / modified in the database
    final CourseCreationOutputBoundary courseCreationOutputBoundary;

    public CourseCreationInteractor(CourseCreationDsGateway courseCreationDSGateway,
                                    CourseCreationOutputBoundary courseCreationOutputBoundary) {
        this.courseCreationDSGateway = courseCreationDSGateway;
        this.courseCreationOutputBoundary = courseCreationOutputBoundary;
    }

    private Course course;

    /**
     * Creates the task in the request model and returns the corresponding response model
     * @param requestModel the input from the instructor
     */
    @Override
    public CourseCreationResponseModel create(CourseCreationRequestModel requestModel) {

        // At least one field left blank
        if (requestModel.getCourseName().equals("") || requestModel.getCourseInstructor().equals("") || requestModel.getTasks().isEmpty()) {
            return courseCreationOutputBoundary.prepareFailView("Please fill in all required information.");
        }

        // checks whether the course id is already in the database / hashmap
        if (courseCreationDSGateway.existsByCourseID(requestModel.getCourseID())) {
            return courseCreationOutputBoundary.prepareFailView("Course already exists.");
        }

        // NOTE: CANNOT BE IMPLEMENTED WITHOUT A TASKFILE
        // checks whether task ids entered exist in the Task database
        // or not? can generate task ids all the same, just won't be linked to a task...
        /*
        ArrayList<String> courseTasks = requestModel.getTasks();
        for (String task : courseTasks) {
            if (TaskMap.findTask(task) == null) {
                return courseCreationOutputBoundary.prepareFailView("one of the IDs does not correspond with a task.");
            }
        }
         */


        // checks passed; course can be created

        // create a new course
        Course courseModel = new Course(requestModel.getCourseName(), requestModel.getCourseInstructor(), requestModel.getTasks());
        try {
            courseCreationDSGateway.saveCourse(courseModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // create response model, sent to presenter
        CourseCreationResponseModel courseResponseModel = new CourseCreationResponseModel(
                course.getCourseID(), course.getTasks());
        return courseCreationOutputBoundary.prepareSuccessView(courseResponseModel);
    }

    public Course getCourse() {
        return this.course;
    }
}

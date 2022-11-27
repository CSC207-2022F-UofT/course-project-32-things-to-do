package course_creation_use_case;

// Use case layer

import entities.*;
import read_write.CourseReadWrite;
import read_write.TaskReadWrite;

import java.util.ArrayList;

public class CourseCreationInteractor implements CourseCreationInputBoundary {
    final CourseCreationDsGateway courseCreationDSGateway;
    final CourseCreationOutputBoundary courseCreationOutputBoundary;
    final CourseMap courseMap;

    public CourseCreationInteractor(CourseCreationDsGateway courseCreationDSGateway, CourseCreationOutputBoundary courseCreationOutputBoundary,
                                    CourseMap courseMap) {
        this.courseCreationDSGateway = courseCreationDSGateway;
        this.courseCreationOutputBoundary = courseCreationOutputBoundary;
        this.courseMap = courseMap;
    }

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

        // Note: Jonathan - no need to check the type of User, students and instructors
        // would have different views because they are in different use cases
        // checks whether the course id is already in the CourseMap (course already exists)

        if (courseCreationDSGateway.existsByCourseID(requestModel.getCourseID())) {
            return courseCreationOutputBoundary.prepareFailView("Course already exists.");
        }

        // checks passed; course can be created

        // create a new course
        Course course = new Course(requestModel.getCourseName(), requestModel.getCourseInstructor(), requestModel.getTasks());
        CourseMap.addCourse(requestModel.getCourseID(), course);

        // course successfully created and saved
        CourseCreationRequestModel courseCreationModel = new CourseCreationRequestModel(course.getCourseName(), course.getCourseInstructor(), course.getTasks());
        courseCreationDSGateway.saveCourse(courseCreationModel);

        // tasks from course (task id will be course name + task number / index of task in arraylist)
        ArrayList<String> courseTasks = course.getTasks();
        for (String task : courseTasks) {
            // need to initialize new task to add course tasks to TaskMap, but unable to since Task is abstract
//            TaskMap.addTask(course.getCourseName() + courseTasks.indexOf(task), task);
        }

        // save course to file
//        CourseReadWrite crw = new CourseReadWrite("src/data/CourseMap");
//        CourseMap.saveToFile(trw);

        // course sent to presenter
        CourseCreationResponseModel courseResponseModel = new CourseCreationResponseModel(
                course.getCourseID(), course.getTasks());
        return courseCreationOutputBoundary.prepareSuccessView(courseResponseModel);
    }
}

package use_cases.course_features.course_creation_use_case;

// Use case layer

import entities.*;

import java.io.IOException;
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

        // need to check that task ids entered exist in the Task database
//        ArrayList<String> courseTasks = requestModel.getTasks();
//        for (String task : courseTasks) {
//            if (TaskMap.findTask(task) == null) {
//                return courseCreationOutputBoundary.prepareFailView("one of the IDs does not correspond with a task.");
//            }
//        }

        // checks passed; course can be created

        // create a new course
        Course course = new Course(requestModel.getCourseName(), requestModel.getCourseInstructor(), requestModel.getTasks());
        CourseMap.addCourse(requestModel.getCourseID(), course);

        // course successfully created and saved
        CourseCreationRequestModel courseCreationModel = new CourseCreationRequestModel(course.getCourseName(), course.getCourseInstructor(), course.getTasks());
        try {
            courseCreationDSGateway.save(courseCreationModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // NEW:
        // extract tasks array

        // OLD
        // tasks from course (task id will be course name + task number / index of task in arraylist)
//        ArrayList<String> courseTasks = course.getTasks();
//        for (String task : courseTasks) {
            // need to initialize new task to add course tasks to TaskMap, but unable to since Task is abstract
//            TaskMap.addTask(course.getCourseName() + courseTasks.indexOf(task), task);
//        }

        // save course to file
//        CourseReadWrite crw = new CourseReadWrite("src/data/CourseMap");
//        CourseMap.saveToFile(trw);

        // course sent to presenter
        CourseCreationResponseModel courseResponseModel = new CourseCreationResponseModel(
                course.getCourseID(), course.getTasks());
        return courseCreationOutputBoundary.prepareSuccessView(courseResponseModel);
    }
}

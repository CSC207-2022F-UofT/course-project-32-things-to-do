package use_cases.course_features.course_enrolment_use_case;

// Use case layer

import entities.*;
import screens.login_registration.FileUser;
import use_cases.login_registration.user_register_usecase.UserRegGateway;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseEnrolmentInteractor implements CourseEnrolmentInputBoundary {

    final CourseEnrolmentDsGateway courseEnrolmentDsGateway; // the course
//    final UserRegGateway userRegGateway; // the student
    final CourseTasksToStudentTodolistDsGateway tasksToTodolistDsGateway;
    final CourseEnrolmentOutputBoundary courseEnrolmentOutputBoundary; // the presenter
    private StudentUser student; // for response model
    private Course enrolledCourse; // for response model

    public CourseEnrolmentInteractor(CourseEnrolmentDsGateway courseEnrolmentDsGateway,
                                     CourseTasksToStudentTodolistDsGateway tasksToDodolistDsGateway,
                                     CourseEnrolmentOutputBoundary courseEnrolmentOutputBoundary) {
        this.courseEnrolmentDsGateway = courseEnrolmentDsGateway;
        this.tasksToTodolistDsGateway = tasksToDodolistDsGateway;
//        this.userRegGateway = userRegGateway;
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
            return courseEnrolmentOutputBoundary.prepareFailView("Please fill in all required information.");
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
        // saveStudentToCourse methods take care of saving the changes to the file?
        try {
            courseEnrolmentDsGateway.saveStudentToCourse(requestModel.getStudentID(), requestModel.getCourseID());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // clone course tasks and save to task map (with new student-related ids)

        // tasks should be properly initialized sent to student

        // get course's tasks by creating an alias of the Courses tasks parameter (needs to be referring to the same tasks)
        ArrayList<String> courseTaskTitlesCopy = courseEnrolmentDsGateway.courseTasks(courseEnrolmentDsGateway.searchForCourse(requestModel.getCourseID()));

        // below: more clearly shows aliasing but warning for redundant variables
        // ArrayList<String> courseTaskTitles = courseEnrolmentDsGateway.courseTasks(courseEnrolmentDsGateway.searchForCourse(requestModel.getCourseID()));
        // ArrayList<String> courseTaskTitlesCopy = courseTaskTitles;

        // make courseTasks into proper id format: courseTasks_instructor_course, add to an arraylist of Tasks
        String instructorName = requestModel.getCourseInstructor();
        String courseName = requestModel.getCourseName();

        ArrayList<String> courseTaskId = new ArrayList<>();
        for (String taskTitleToId : courseTaskTitlesCopy) {
            taskTitleToId = taskTitleToId + "_" + instructorName + "_" + courseName;
            courseTaskId.add(taskTitleToId);
        }

        // get task id : task object pairs from taskmap, save to old task id map
        HashMap<String, Task> oldTaskIdMap = new HashMap<>();
        for (String oldTaskId : courseTaskId) {
            Task taskValue = TaskMap.findTask(oldTaskId);
            oldTaskIdMap.put(oldTaskId, taskValue);
        }

        // for each task id : Task pair, change the key name to courseTasks_student_course, add key-value pair to arraylist
        // create new arraylist
        HashMap<String, Task> newTaskIdMap = new HashMap<>();
        // iterate over original arraylist and put key-value pair into new arraylist with modified key
        for (HashMap.Entry<String, Task> entry : oldTaskIdMap.entrySet()) {
            // key has old format title_instructor_course
            // want to title, so split by _ which gives 'title', 'instructor', 'course'
            // take first index which is just 'title'
            // concatenating everything gives title_student_course
            newTaskIdMap.put(entry.getKey().split("_")[0] + "_" + requestModel.getStudentID() + "_" + courseName,
                    (Task) oldTaskIdMap.entrySet());
        }

        // add map with new task ids to TaskMap
        // TODO: read file, make edits, then save changes
        for (Map.Entry<String, Task> entry : newTaskIdMap.entrySet()) {
            TaskMap.addTask(entry.getKey(), entry.getValue());
        }

        // take the keys of the key-value pair and add to student's to do list
        // get the set of all keys of the new task id map and add to arraylist of strings
        ArrayList<String> newTaskIds = new ArrayList<>(newTaskIdMap.keySet());

        // add new task ids to the student's task list
        tasksToTodolistDsGateway.addSaveTasksToTodolist(requestModel.getStudentID(), newTaskIds);

        // add course to student's 'courses' parameter
        tasksToTodolistDsGateway.addCourseToStudent(requestModel.getCourseID(), requestModel.getStudentID());

        // create response model, sent to presenter
        CourseEnrolmentResponseModel enrolmentResponseModel = new CourseEnrolmentResponseModel(
                student.getName(), enrolledCourse.getCourseID(), courseTaskTitlesCopy);

        return courseEnrolmentOutputBoundary.prepareSuccessView(enrolmentResponseModel);
    }
}

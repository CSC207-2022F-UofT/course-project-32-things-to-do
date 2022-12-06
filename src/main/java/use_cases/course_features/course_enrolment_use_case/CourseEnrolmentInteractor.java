package use_cases.course_features.course_enrolment_use_case;

// Use case layer

import entities.*;
import screens.login_registration.FileUser;
import use_cases.login_registration.user_register_usecase.UserRegGateway;

import java.io.IOException;
import java.lang.reflect.Array;
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

        // find + get taskId-taskValue pairs from TaskMap, save to (temporary) oldTaskIdMap
        HashMap<String, Task> oldTaskIdMap = new HashMap<>();
        for (String oldTaskId : courseTaskId) {
            Task taskValue = TaskMap.findTask(oldTaskId); // gets value from key
            oldTaskIdMap.put(oldTaskId, taskValue); // add key-value pair to new map
        }

        // for each task id : Task pair, change the key name to courseTasks_student_course, add key-value pair to arraylist
        HashMap<String, Task> newTaskIdMap = new HashMap<>(); // initialize new TaskIdMap

        // create 2 parallel arraylists, one for keys, one for values
        ArrayList<String> oldKeys = courseTaskId; // could also do (ArrayList) oldTaskIdMap.keySet()
        ArrayList<Task> values = (ArrayList) oldTaskIdMap.entrySet();

        ArrayList<String> newKeys = new ArrayList<>();
        // change key name from title_instructor_course to title_student_course
        for (String taskId :oldKeys) {
            // change key name from title_inst_course to title_student_course
            String newKey = taskId.replace(requestModel.getCourseInstructor(), requestModel.getStudentID());
            newKeys.add(newKey);
        }

        // newKeys and values should be of equal length with each index referring to a pair
        // ie. newKey[0], values[0] should be the old Task object but with the new course task id name!

        // iterate through one of the arraylists (let's do keys)
        for (int i = 0; i <= newKeys.size(); i++) {
            // add key-value pairs to newTaskIdMap
            newTaskIdMap.put(newKeys.get(i), values.get(i));
        }

        // add all newTaskIdMap key-value pairs to TaskMap
        // TODO: read file, make edits, then save changes
        TaskMap.addTasks(newTaskIdMap);

        // add new task ids to the student's to-do list (of task ids)
        try {
            tasksToTodolistDsGateway.addSaveTasksToTodolist(requestModel.getStudentID(), newKeys);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // add course to student's 'courses' parameter
        try {
            tasksToTodolistDsGateway.addCourseToStudent(requestModel.getCourseID(), requestModel.getStudentID());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // create response model, sent to presenter
        CourseEnrolmentResponseModel enrolmentResponseModel = new CourseEnrolmentResponseModel(
                student.getName(), enrolledCourse.getCourseID(), courseTaskTitlesCopy);

        return courseEnrolmentOutputBoundary.prepareSuccessView(enrolmentResponseModel);
    }
}

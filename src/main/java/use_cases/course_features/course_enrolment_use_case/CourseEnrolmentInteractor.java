package use_cases.course_features.course_enrolment_use_case;

// Use case layer

import entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CourseEnrolmentInteractor implements CourseEnrolmentInputBoundary {
    final CourseEnrolmentUserDsGateway userDsGateway;
    final CourseEnrolmentCourseDsGateway courseDsGateway;
    final CourseEnrolmentTaskDsGateway taskDsGateway;
    final CourseEnrolmentOutputBoundary enrolmentOutputBoundary;

    public CourseEnrolmentInteractor(CourseEnrolmentUserDsGateway userDsGateway,
                                     CourseEnrolmentCourseDsGateway courseDsGateway,
                                     CourseEnrolmentTaskDsGateway taskDsGateway,
                                     CourseEnrolmentOutputBoundary enrolmentOutputBoundary) {
        this.userDsGateway = userDsGateway;
        this.courseDsGateway = courseDsGateway;
        this.taskDsGateway = taskDsGateway;
        this.enrolmentOutputBoundary = enrolmentOutputBoundary;
    }

    @Override
    public void enrol(CourseEnrolmentRequestModel requestModel) {
        // At least one field left blank
        if (requestModel.getCourseName().equals("") || requestModel.getCourseInstructor().equals("")
                || requestModel.getStudentID().equals("")) {
            enrolmentOutputBoundary.prepareFailView("Please fill in all required information.");
            return;
        }

        // checks if given course id is in the map of existing courses
        if (!courseDsGateway.existsByCourseID(requestModel.getCourseID())) {
            enrolmentOutputBoundary.prepareFailView("Entered information does not correspond to an existing course.");
            return;
        }

        // all checks passed; background work for enrolment starts to happen

        // 1. course gateway

        // add student id to Course parameter 'students'
        try {
            courseDsGateway.saveStudentToCourse(requestModel.getStudentID(), requestModel.getCourseID());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // get course's task parameter (which takes the task TITLES)
        // needed for task gateway step
        ArrayList<String> courseTaskTitles = courseDsGateway.getCourseTasks(requestModel.getCourseID());

        // 2. task gateway

        // transform task titles to actual task ids
        String instructorName = requestModel.getCourseInstructor();
        String courseName = requestModel.getCourseName();

        ArrayList<String> courseTaskIDs = new ArrayList<>();
        for (String taskTitleToId : courseTaskTitles) {
            taskTitleToId = taskTitleToId + "_" + instructorName + "_" + courseName;
            courseTaskIDs.add(taskTitleToId);
        }

        // for each id in courseTaskIDs, get Task associated with it, save to temp map
        HashMap<String, Task> oldTaskIdMap = new HashMap<>();
        for (String oldTaskId : courseTaskIDs) {
            Task taskValue = taskDsGateway.getTask(oldTaskId); // gets value from key
            oldTaskIdMap.put(oldTaskId, taskValue); // add key-value pair to new map
        }

        // iterate through courseTaskIDs, get corresponding Task object, add to arraylist
        ArrayList<Task> values = new ArrayList<>();
        for (String key: courseTaskIDs) {
            Task value = oldTaskIdMap.get(key);
            values.add(value);
        }

        // make newKeys with task id to title_student_course
        ArrayList<String> newKeys = new ArrayList<>();
        // change key name from title_instructor_course to title_student_course
        for (String taskId : courseTaskIDs) {
            // change key name from title_inst_course to title_student_course
            String newKey = taskId.replace(requestModel.getCourseInstructor(), requestModel.getStudentID());
            newKeys.add(newKey);
        }

        // add newKeys and values as a key-value pair to a temp new map
        HashMap<String, Task> newTaskIdMap = new HashMap<>();
        for (int i = 0; i < newKeys.size(); i++) {
            // add key-value pairs to newTaskIdMap
            newTaskIdMap.put(newKeys.get(i), values.get(i));
        }

        // add newTaskIdMap to FileTaskMap
        taskDsGateway.saveNewMaptoMap(newTaskIdMap);

        // 3. user gateway

        // add course id to student's 'courses' parameter
        try {
            userDsGateway.addCourseToStudent(requestModel.getCourseID(), requestModel.getStudentID());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // add arraylist newKeys to student's to do list
        try {
            userDsGateway.addTasksToTodolist(requestModel.getStudentID(), newKeys);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // saving changes
        // TODO: no need because individual method calls already saves?

        // create response model, send to presenter
        CourseEnrolmentResponseModel enrolmentResponseModel = new CourseEnrolmentResponseModel(
                requestModel.getStudentID(), requestModel.getCourseID(), newKeys);

        enrolmentOutputBoundary.prepareSuccessView(enrolmentResponseModel);


    }
}

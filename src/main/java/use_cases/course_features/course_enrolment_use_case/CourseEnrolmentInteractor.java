package use_cases.course_features.course_enrolment_use_case;

// Use case layer

import entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementation of the business logic on the entities
 */
public class CourseEnrolmentInteractor implements CourseEnrolmentInputBoundary {
    final CourseEnrolmentCourseDsGateway courseDsGateway;
    final CourseEnrolmentTaskDsGateway taskDsGateway;
    final CourseEnrolmentOutputBoundary enrolmentOutputBoundary;

    public CourseEnrolmentInteractor(CourseEnrolmentCourseDsGateway courseDsGateway,
                                     CourseEnrolmentTaskDsGateway taskDsGateway,
                                     CourseEnrolmentOutputBoundary enrolmentOutputBoundary) {
        this.courseDsGateway = courseDsGateway;
        this.taskDsGateway = taskDsGateway;
        this.enrolmentOutputBoundary = enrolmentOutputBoundary;
    }

    /**
     * the main controller of the interactor that will be calling the helper methods
     */
    @Override
    public void enrol(CourseEnrolmentRequestModel requestModel) {
        // initialization
        // TODO: might be illegal, can't call screen? -> HashMap<String, Task> taskHashMap = TaskMap.getTaskMap();
        // TODO: currentuser set to null rn because did not run from main --> DO INTERACTOR TEST PLS
        StudentUser currentUser = (StudentUser) CurrentUser.getCurrentUser();

        // At least one field left blank
        if (requestModel.getCourseName().equals("") || requestModel.getCourseInstructor().equals("")) {
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
            courseDsGateway.saveStudentToCourse(currentUser.getName(), requestModel.getCourseID());
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
            taskTitleToId = taskTitleToId + "_" + instructorName + "_none";
            courseTaskIDs.add(taskTitleToId);
        }

        // for each id in courseTaskIDs, get Task associated with it, save to temp map
        HashMap<String, Task> oldTaskIdMap = new HashMap<>();
        for (String oldTaskId : courseTaskIDs) {
            Task taskValue = TaskMap.getTaskMap().get(oldTaskId); // gets value from key
            // TODO: gateway way / Task taskValue = taskDsGateway.getTask(oldTaskId); // gets value from key
            oldTaskIdMap.put(oldTaskId, taskValue); // add key-value pair to new map
        }

        // iterate through courseTaskIDs, get corresponding Task object, add to arraylist
        ArrayList<Task> values = new ArrayList<>();
        for (String key: courseTaskIDs) {
            Task oldValue = oldTaskIdMap.get(key);
            // makes a copy of the old value based on its Task type
            Task newValue;
            if (oldValue instanceof Test) {
                newValue = ((Test) oldValue).getTestCopy();
                values.add(newValue);
            } else if (oldValue instanceof Assignment) {
                newValue = ((Assignment) oldValue).getAssignmentCopy();
                values.add(newValue);
            }
        }

        // make newKeys with task id to title_student_course
        ArrayList<String> newKeys = new ArrayList<>();
        // change key name from title_instructor_course to title_student_course
        for (String taskId : courseTaskIDs) {
            // change key name from title_inst_course to title_student_course
            String newKey = taskId.replace(
                    requestModel.getCourseInstructor() + "_none",
                    currentUser.getName() + "_" + courseName);
            newKeys.add(newKey); // a1_paul_none --> a1_julie_csc207
        }

        // add newKeys and values as a key-value pair to a temporary new map
        HashMap<String, Task> newTaskIdMap = new HashMap<>();
        for (int i = 0; i < newKeys.size(); i++) {
            // add key-value pairs to newTaskIdMap
            // initialize the key of the new task copy (currently is initialized to an empty string)
            values.get(i).setId(newKeys.get(i));
            newTaskIdMap.put(newKeys.get(i), values.get(i));
        }

        // add newTaskIdMap to FileTaskMap, method saves the task map
        taskDsGateway.saveNewMaptoMap(newTaskIdMap);

        // 3. current user stuff TODO: NOT user gateway

        // add course id to student's 'courses' parameter
        currentUser.getCourses().add(requestModel.getCourseID());

        // add arraylist newKeys to student's to do list
        currentUser.getToDoList().addAll(newKeys);

        // saving changes TODO: no need to save if current user doesn't log out / close program?

        // create response model, send to presenter
        CourseEnrolmentResponseModel enrolmentResponseModel = new CourseEnrolmentResponseModel(
                requestModel.getCourseID(), newKeys);

        enrolmentOutputBoundary.prepareSuccessView(enrolmentResponseModel);

    }
}

package screens;

import entities.Course;
import entities.Task;
import entities.User;
import progress_tracker_use_case.ProgressTrackerInputBoundary;
import progress_tracker_use_case.ProgressTrackerRequestModel;
import progress_tracker_use_case.ProgressTrackerResponseModel;

import java.util.HashMap;

/**
 * Controller for the Progress Tracker Use Case
 * Located in the Interface Adapter Layer, triggers the Interactor
 */

public class ProgressTrackerController {

    final ProgressTrackerInputBoundary progressInput;
    final String courseName;
    final User studentUser;
    HashMap<String, Task> allTasks;
    HashMap<String, User> allUsers;
    HashMap<String, Course> allCourses;

    public ProgressTrackerController(ProgressTrackerInputBoundary progressInput, User studentUser,
                                     String courseName, HashMap<String, Task> allTasks,
                                     HashMap<String, User> allUsers, HashMap<String, Course> allCourses) {
        this.progressInput = progressInput;
        this.studentUser = studentUser;
        this.courseName = courseName;
        this.allTasks = allTasks;
        this.allUsers = allUsers;
        this.allCourses = allCourses;
    }

    ProgressTrackerResponseModel trackProgress(String courseName, String newGradeTaskName, String newGrade,
                                               String newGoalGrade) {
        ProgressTrackerRequestModel requestModel = new ProgressTrackerRequestModel(
                studentUser, courseName, allTasks, allUsers, allCourses, newGradeTaskName, newGrade, newGoalGrade);

        return progressInput.trackProgress(requestModel);
    }
}

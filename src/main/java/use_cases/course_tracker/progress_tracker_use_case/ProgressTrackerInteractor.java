package use_cases.course_tracker.progress_tracker_use_case;

import entities.*;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentCourseDsGateway;
import use_cases.course_tracker.CourseTrackerInteractor;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * The Progress Tracker Use Case Interactor (use case layer)
 * Implements business logic on entities
 */
public class ProgressTrackerInteractor extends CourseTrackerInteractor implements ProgressTrackerInputBoundary{

    private final ProgressTrackerOutputBoundary outputBoundary;
    private final CourseEnrolmentCourseDsGateway courseAccess;
    public ProgressTrackerInteractor(ProgressTrackerOutputBoundary outputBoundary,
                                     CourseEnrolmentCourseDsGateway courseAccess) {
        this.courseAccess = courseAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * The main controller of this interactor. Sequentially calls all the helper methods.
     */
    @Override
    public void trackProgress(ProgressTrackerRequestModel progressTrackerRequestModel) {
        try {
            if (!CurrentUser.isStudent()) { //should never occur!
                throw new RuntimeException("You are not a registered student!");
            }

            String courseName = progressTrackerRequestModel.getcourseName();

            //parse user input
            double newGrade = stringToDouble(progressTrackerRequestModel.getNewGrade());
            double newGoalGrade = stringToDouble(progressTrackerRequestModel.getNewGoalGrade());

            //retrieve course ID based on inputted course name
            String courseID = courseNameToID(courseName, courseAccess);

            //query aggregate task map for all of this student's tasks in this course
            ArrayList<Task> studentCourseTasks = getStudentCourseTasks(courseName);

            //if a newGrade was inputted, mutate the corresponding task object
            if (newGrade != -1) {
                setTaskGrade(studentCourseTasks, progressTrackerRequestModel.getNewGradeTaskName(), newGrade);
            }

            //if a newGoalGrade was inputted, mutate the corresponding studentUser object
            if (newGoalGrade != -1) {
                setCourseGoalGrade(courseID, newGoalGrade);
            }

            //calculations
            double courseProgress = courseProgressCalculator(studentCourseTasks);
            double mockGrade = mockGradeCalculator(studentCourseTasks);

            //if goal grade exists for this course, more calculations
            double requiredAverage;
            Double goalGrade = ((StudentUser) CurrentUser.getCurrentUser()).getDesiredGrades().get(courseID);
            if (goalGrade != null) {
                requiredAverage = requiredAverageCalculator(goalGrade, mockGrade, studentCourseTasks);
            } else {
                requiredAverage = -1; //no goal grade inputted yet!!
            }

            //collect all the tasks that are still ungraded
            ArrayList<String> ungradedTasks = getUngradedTasks(studentCourseTasks);

            ProgressTrackerResponseModel responseModel = new ProgressTrackerResponseModel(courseProgress, mockGrade,
                    requiredAverage, ungradedTasks);
            outputBoundary.format(responseModel);

        } catch(Exception e) {
            outputBoundary.failView(e.getMessage());
        }

    }

    /**
     * Calculates the percent of completed tasks out of a student's total tasks in a course.
     *
     * @param courseTasks list of a student's total Gradable tasks in a course
     *                    (precondition: weightage attribute is in DECIMAL percent)
     * @return the total weight of completed tasks (in percent)
     */
    private double courseProgressCalculator(ArrayList<Task> courseTasks) {
        double completedWeightage = 0;

        for (Task task: courseTasks) {
            if (task.getComplete()) {
                completedWeightage += ((Gradable) task).getWeightage();
            }
        }

        if (0 <= completedWeightage && completedWeightage <= 1.0) {
            return completedWeightage * 100; //multiply by 100%
        } else {
            throw new RuntimeException("Out of bounds mock grade of " + completedWeightage +
                    " resulting from invalid input. Please check the weightage of your tasks, they should be in " +
                    "DECIMAL percents. E.g. 0.8 = 80%");
        }
    }

    /**
     * From a list of Gradable tasks, return a new list of strings of the titles of all the Task objects that
     * are Gradable and have a gradeReceived value of -1 from the given studentTasks
     *
     * @param studentTasks a list of the logged-in student's Task tasks for a course
     * @return a list of strings
     */
    private ArrayList<String> getUngradedTasks(ArrayList<Task> studentTasks) {
        ArrayList<String> ungradedTasks = new ArrayList<>();

        for (Task task: studentTasks) {
            if (task instanceof Gradable && ((Gradable) task).getGradeReceived() == -1) {
                ungradedTasks.add(task.getTitle());
            }
        }

        return ungradedTasks;
    }

    /**
     * Mutates a task in the aggregate task map, setting the given task's received grade to the inputted double.
     *
     * @param studentTasks a list of all the student's Gradable tasks in the given course
     * @param newGradeTaskName the String name of the task for the inputted grade
     * @param newGrade a double representing the new received grade
     */
    public void setTaskGrade(ArrayList<Task> studentTasks, String newGradeTaskName, double newGrade) {
        if (0 > newGrade || newGrade > 100) {
            throw new RuntimeException("Entered grade is out of bounds.");
        }

        for (Task task: studentTasks) {
            if (task.getTitle().equals(newGradeTaskName)) {
                if (TaskMap.findTask(task.getId()).getComplete()) {
                    ((Gradable) TaskMap.findTask(task.getId())).setGradeReceived(newGrade);
                    return;
                } else {
                    throw new RuntimeException("Set this task to 'Complete' before adding its grade.");
                }
            }
        }

        throw new RuntimeException("No such task with that task name exists.");
    }

    /**
     * Mutates a user in the aggregate user map, adding/modifying the given desired grade for the given course.
     *
     * @param courseID a string representing the course id of the given course
     * @param newGoalGrade the new desired grade (in percent) for the given course
     */
    public void setCourseGoalGrade(String courseID, double newGoalGrade) {
        if (0 > newGoalGrade || newGoalGrade > 100) {
            throw new RuntimeException("Entered desired grade is out of bounds.");
        }

        ((StudentUser) CurrentUser.getCurrentUser()).getDesiredGrades().put(courseID, newGoalGrade);

    }

}

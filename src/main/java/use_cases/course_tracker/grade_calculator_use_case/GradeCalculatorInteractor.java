package use_cases.course_tracker.grade_calculator_use_case;

import entities.Assignment;
import entities.CurrentUser;
import entities.Gradable;
import entities.Task;
import use_cases.course_tracker.CourseTrackerInteractor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Use case interactor for Grade Calculator Use Case
 * Located in the Use Case layer
 */
public class GradeCalculatorInteractor extends CourseTrackerInteractor implements GradeCalculatorInputBoundary{

    private final GradeCalculatorOutputBoundary presenter;
    private final ArrayList<String> targetTasksTitles = new ArrayList<>();
    private Double targetCourseGrade;

    public GradeCalculatorInteractor(GradeCalculatorOutputBoundary presenter) {
        this.presenter = presenter;
    }

    /**
     * The main controller of this interactor. Sequentially calls all the helper methods.
     */
    @Override
    public void calculateGrade(GradeCalculatorRequestModel requestModel) {
        try {
            if (!CurrentUser.isStudent()) { //should never occur!
                throw new RuntimeException("You are not a registered student!");
            }

            //take data from screen input
            String courseName = requestModel.getCourseName();
            ArrayList<String> ungradedTasks = requestModel.getUngradedTasks();

            //parse user input
            String[] userInput = requestModel.getUserInput().split(",");
            if (userInput.length != ungradedTasks.size() && userInput.length > 0) {
                throw new RuntimeException("Invalid length of input.");
            }

            //map the inputted grades with corresponding task titles
            HashMap<String, Double> ungradedTaskToGrade = mapUngradedTaskToGrade(userInput, ungradedTasks);
            //check that at least one target task and course grade goal was inputted
            if (targetCourseGrade == null || targetTasksTitles.isEmpty()) {
                throw new RuntimeException("No valid target task grade was read from input.");
            }

            //retrieve course ID based on inputted course name
            String courseID = courseNameToID(courseName);

            //query aggregate task map for all of this student's tasks in this course
            ArrayList<Task> studentCourseTasks = getStudentCourseTasks(courseID);

            //create a copy of studentCourseTasks including goal/predicted grades of ungraded tasks
            ArrayList<Task> studentCourseTasksCopy = copyStudentTasks(studentCourseTasks, ungradedTaskToGrade);

            //use parent helper functions to do calculations
            double mockGrade = mockGradeCalculator(studentCourseTasksCopy);
            double requiredGrade = requiredAverageCalculator(targetCourseGrade, mockGrade, studentCourseTasksCopy);

            GradeCalculatorResponseModel responseModel = new GradeCalculatorResponseModel(requiredGrade);

            presenter.display(responseModel);

        } catch (Exception e) {
            presenter.failView(e.getMessage());
        }
    }

    /**
     * Function maps the ungraded task names input by the user to the corresponding inputted desired/predicated grades
     * A '*' character in the user input indicates a target task and the course goal grade.
     *
     * @param userInput an array of strings representing the desired/predicted grades of ungraded tasks
     * @param ungradedTasks a list of the ungraded task titles of the current user for the given course
     * @return a map mapping task title strings to Double grades
     */
    private HashMap<String, Double> mapUngradedTaskToGrade(String[] userInput, ArrayList<String> ungradedTasks) {
        HashMap<String, Double> ungradedTaskToGrade = new HashMap<>();

        for (int i = 0; i < ungradedTasks.size(); i++) {
            if (userInput[i].contains("*")) {
                if (userInput[i].length() > 1) {
                    targetTasksTitles.add(ungradedTasks.get(i));
                    targetCourseGrade = stringToDouble(userInput[i].substring(1));
                } else {
                    throw new RuntimeException("Invalid grade input. Expected a number.");
                }
            } else {
                ungradedTaskToGrade.put(ungradedTasks.get(i), stringToDouble(userInput[i]));
            }
        }

        return ungradedTaskToGrade;
    }

    /**
     * Create a duplicate of the given studenCourseTasks, except replacing any tasks that are ungraded (and are NOT
     * target tasks) with a dummy task object containing only the title, gradeReceived and gradeWeightage.
     *
     * @param studentCourseTasks the logged-in student's tasks in the given course
     * @param ungradedTaskToGrade a list of strings representing the task titles of ungraded
     * @return a list of Task objects as specified
     */
    private ArrayList<Task> copyStudentTasks(ArrayList<Task> studentCourseTasks,
                                             HashMap<String, Double> ungradedTaskToGrade) {
        ArrayList<Task> studentCourseTasksCopy = new ArrayList<>();

        for (Task task: studentCourseTasks) {
            if (ungradedTaskToGrade.containsKey(task.getTitle())) {
                //using Assignment because Task object must be Gradable
                Assignment dummyTask = new Assignment(task.getTitle(), "", null,
                        ((Gradable)task).getWeightage());
                dummyTask.setComplete();
                dummyTask.setGradeReceived(ungradedTaskToGrade.get(task.getTitle()));
                studentCourseTasksCopy.add(dummyTask);
            } else if (targetTasksTitles.contains(task.getTitle())) {
                //leave target tasks as "ungraded" with gradeReceived set to -1 as default
                //all other tasks are added as they are
                studentCourseTasksCopy.add(task);
            }
        }

        return studentCourseTasksCopy;
    }
}
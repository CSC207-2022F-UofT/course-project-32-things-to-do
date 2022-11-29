package use_cases.course_tracker.progress_tracker_use_case;

import entities.*;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * The Progress Tracker Use Case Interactor (use case layer)
 * Implements business logic on entities
 */
public class ProgressTrackerInteractor implements ProgressTrackerInputBoundary{

    final ProgressTrackerOutputBoundary outputBoundary;

    public ProgressTrackerInteractor(ProgressTrackerOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * The main controller of this interactor. Sequentially calls all the helper methods.
     */
    @Override
    public ProgressTrackerResponseModel trackProgress(ProgressTrackerRequestModel
                                                                       progressTrackerRequestModel) {

        try {

            if (!(progressTrackerRequestModel.getStudentUser() instanceof StudentUser)) {
                throw new RuntimeException("You are not a registered student");
            }
            StudentUser studentUser = (StudentUser) progressTrackerRequestModel.getStudentUser();

            String courseName = progressTrackerRequestModel.getcourseName();
            HashMap<String, Course> allCourses = progressTrackerRequestModel.getAllCourses();
            HashMap<String, Task> allTasks = progressTrackerRequestModel.getAllTasks();
            HashMap<String, User> allUsers = progressTrackerRequestModel.getAllUsers();

            //parse user input
            double newGrade = stringToDouble(progressTrackerRequestModel.getNewGrade());
            double newGoalGrade = stringToDouble(progressTrackerRequestModel.getNewGoalGrade());

            //retrieve course ID based on inputted course name
            String courseID = courseNameToID(courseName, studentUser, allUsers, allCourses);

            //query aggregate task map for this student's tasks in this course
            ArrayList<Task> studentCourseTasks = getStudentCourseTasks(allTasks, courseID, studentUser);

            //if a newGrade was inputted, mutate the corresponding task object
            if (newGrade != -1) { //TODO extend: add multiple grades at once? (remove grades? <- NO! Overwrite only)
                setTaskGrade(allTasks, studentCourseTasks, progressTrackerRequestModel.getNewGradeTaskName(), newGrade);
            }

            //if a newGoalGrade was inputted, mutate the corresponding studentUser object
            if (newGoalGrade != -1) {
                setCourseGoalGrade(allUsers, studentUser, courseID, newGoalGrade);
            }

            //calculations
            double courseProgress = courseProgressCalculator(studentCourseTasks);
            double mockGrade = mockGradeCalculator(studentCourseTasks);

            //if goal grade exists for this course, more calculations
            double requiredAverage;
            Double goalGrade = (Double) studentUser.getDesiredGrades().get(courseID);
            if (goalGrade != null) {
                requiredAverage = requiredAverageCalculator(goalGrade, mockGrade, studentCourseTasks);
            } else {
                requiredAverage = -1; //no goal grade inputted yet!!
            }

            ProgressTrackerResponseModel responseModel = new ProgressTrackerResponseModel(courseProgress, mockGrade,
                    requiredAverage);
            return outputBoundary.display(responseModel);

        } catch(Exception e) {
            return outputBoundary.failView(e.getMessage());
        }

    }

    /**
     * Return a double with the numerical value represented by the string.
     * -1.0 is returned for a null or empty string. Exception is thrown for non-numerical non-empty string.
     *
     * @param string string to be converted to type double
     * @return the value of string with type double
     */
    private double stringToDouble(String string) {
        if (string == null || string.isEmpty()) {
            return -1.0;
        } else {
            try {
                return Double.valueOf(string);
            } catch(Exception e) {
                throw new RuntimeException("Entered input is invalid. It should be a (decimal) number " +
                        "with no special characters.");
            }
        }
    }

    /**
     * Return a String representing the course ID of the student's course with given course Name
     *
     * @param courseName a String representing the name of the student's course
     * @param studentUser a String representing the user ID of the student
     * @param allUsers an aggregate entity mapping all program users to their user ID
     * @param allCourses an aggregate entity mapping all program courses to their user ID
     * @return the course ID of the student's course
     */
    private String courseNameToID(String courseName, StudentUser studentUser, HashMap<String, User> allUsers,
                                  HashMap<String, Course> allCourses) {

        ArrayList<String> allCourseIDs = studentUser.getCourses();

        for (String courseID: allCourseIDs) {
            if (allCourses.get(courseID).getCourseName().equals(courseName)) {
                return courseID;
            }
        }

        throw new RuntimeException("No course with that course name was found.");

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
     * Calculates the grade so far based on total weightage of graded tasks.
     *
     * @param courseTasks List of Task objects for given course for given student
     *                    (precondition: all Task objects implement Gradable, receivedGrade is in percent and
     *                    weightage attribute is in decimal percent)
     * @return the student's grade so far in given course
     */
    private double mockGradeCalculator(ArrayList<Task> courseTasks) {
        double mockGrade = 0.0;
        double gradedWeightage = 0.0;

        for (Task task: courseTasks) {
            if (((Gradable) task).getGradeReceived() != -1) {
                mockGrade += ((Gradable) task).getWeightage() * ((Gradable) task).getGradeReceived();
                gradedWeightage += ((Gradable) task).getWeightage();
            }
        }

        if (mockGrade == 0) {
            return 0;
        }

        //check that this is a valid grade
        if (0 <= mockGrade && mockGrade <= 100) {
            return mockGrade / gradedWeightage; // already percentage (e.g., 74.1667%)
        } else {
            throw new RuntimeException("Out of bounds mock grade of " + mockGrade + " resulting from invalid input.");
        }
    }

    /**
     * Calculates the required average on remaining ungraded tasks for student in given course, based on the
     * inputted goalGrade. Exceptions are thrown if the result is 0< or >100.
     *
     * @param goalGrade the inputted desired grade of the student in the course (in percent)
     * @param mockGrade the inputted grade so far of the student in the course (in percent)
     * @param courseTasks a list of the student's tasks in this course
     * @return a double representing the required average in percent
     */
    private double requiredAverageCalculator(double goalGrade, double mockGrade, ArrayList<Task> courseTasks) {
        if (0 <= goalGrade && goalGrade <= 100) {
            double ungradedWeight = 0.0;

            for (Task task: courseTasks) {
                if (((Gradable) task).getGradeReceived() == -1) {
                    ungradedWeight += ((Gradable) task).getWeightage();
                }
            }

            double requiredAverage = (goalGrade/100 - mockGrade/100 * (1.0 - ungradedWeight))  / ungradedWeight;
            requiredAverage = requiredAverage * 100; // from decimal (0.88) to percentage (88%)

            if (requiredAverage < 0) {
                throw new RuntimeException("Aim higher! You are already guaranteed that goal grade!");
            } else if (requiredAverage > 100) {
                throw new RuntimeException("The required average for that goal grade is greater than 100.");
            } else {
                return requiredAverage;
            }

        } else {
            throw new RuntimeException("Goal grade input out of bounds, it should be in percent. E.g. 80 = 80%");
        }
    }

    /**
     * Return all Task tasks for given courseID and studentUser that implement Gradable
     *
     * @param allTasks an aggregate entity mapping all program tasks to their task ID
     * @param courseID a String representing the course ID of the given course
     * @param studentUser a string representing the user ID of the logged in student
     * @return a list of all the student's Gradable tasks in the given course
     */
    private ArrayList<Task> getStudentCourseTasks(HashMap<String, Task> allTasks, String courseID,
                                                  StudentUser studentUser) {
        ArrayList<Task> studentCourseTasks = new ArrayList<Task>();

        for (String mapKey: allTasks.keySet()) {
            if (mapKey.contains(courseID) && mapKey.contains(studentUser.getName())) {
                if (allTasks.get(mapKey) instanceof Gradable) {
                    studentCourseTasks.add(allTasks.get(mapKey));
                }
            }
        }

        return studentCourseTasks;
    }

    /**
     * Mutates a task in the aggregate task map, setting the given task's received grade to the inputted double.
     *
     * @param allTasks an aggregate entity mapping all program tasks to their task ID
     * @param studentTasks a list of all the student's Gradable tasks in the given course
     * @param newGradeTaskName the String name of the task for the inputted grade
     * @param newGrade a double representing the new received grade
     */
    public void setTaskGrade(HashMap<String, Task> allTasks, ArrayList<Task> studentTasks,
                                           String newGradeTaskName, double newGrade) {
        if (0 > newGrade || newGrade > 100) {
            throw new RuntimeException("Entered grade is out of bounds.");
        }

        for (Task task: studentTasks) {
            if (task.getTitle().equals(newGradeTaskName)) {
                if (allTasks.get(task.getId()).getComplete()) {
                    ((Gradable) allTasks.get(task.getId())).setGradeReceived(newGrade);
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
     * @param allUsers an aggregate entity mapping all program users to their user ID
     * @param studentUser a string representing the user id of the logged in student
     * @param courseID a string representing the course id of the given course
     * @param newGoalGrade the new desired grade (in percent) for the given course
     */
    public void setCourseGoalGrade(HashMap<String, User> allUsers, StudentUser studentUser, String courseID,
                                      double newGoalGrade) {
        if (0 > newGoalGrade || newGoalGrade > 100) {
            throw new RuntimeException("Entered desired grade is out of bounds.");
        }

        studentUser.getDesiredGrades().put(courseID, newGoalGrade);

    }

}

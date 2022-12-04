package use_cases.course_tracker;

import entities.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract use case interactor
 * Parent interactor for the Progress Tracker and Grade Calculator use cases
 */
public abstract class CourseTrackerInteractor {
    /**
     * Return a double with the numerical value represented by the string.
     * -1.0 is returned for a null or empty string. Exception is thrown for non-numerical non-empty string.
     *
     * @param string string to be converted to type double
     * @return the value of string with type double
     */
    protected double stringToDouble(String string) {
        if (string == null || string.isEmpty()) {
            return -1.0;
        } else {
            try {
                return Double.parseDouble(string);
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
     * @return the course ID of the student's course
     */
    protected String courseNameToID(String courseName) {

        ArrayList<String> allCourseIDs = ((StudentUser) CurrentUser.getCurrentUser()).getCourses();

        for (String courseID: allCourseIDs) {
            if (CourseMap.findCourse(courseID).getCourseName().equals(courseName)) {
                return courseID;
            }
        }

        throw new RuntimeException("None of your enrolled courses match that course name.");

    }

    /**
     * Return all Task tasks for given courseID and studentUser that implement Gradable
     *
     * @param courseID a String representing the course ID of the given course
     * @return a list of all the student's Gradable tasks in the given course
     */
    protected ArrayList<Task> getStudentCourseTasks(String courseID) {
        StudentUser studentUser = (StudentUser) CurrentUser.getCurrentUser();
        HashMap<String, Task> allTasks = TaskMap.getTaskMap();

        ArrayList<Task> studentCourseTasks = new ArrayList<>();

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
     * Calculates the grade so far based on total weightage of graded tasks.
     *
     * @param courseTasks List of Task objects for given course for given student
     *                    (precondition: all Task objects implement Gradable, receivedGrade is in percent and
     *                    weightage attribute is in decimal percent)
     * @return the student's grade so far in given course
     */
    protected double mockGradeCalculator(ArrayList<Task> courseTasks) {
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
     * Calculates the required average grade for remaining ungraded weightage for student in given course, based on the
     * inputted goalGrade. Exceptions are thrown if the result is 0< or >100.
     *
     * @param goalGrade the inputted desired grade of the student in the course (in percent)
     * @param mockGrade the inputted grade so far of the student in the course (in percent)
     * @param courseTasks a list of the student's tasks in this course
     * @return a double representing the required average in percent
     */
    protected double requiredAverageCalculator(double goalGrade, double mockGrade, ArrayList<Task> courseTasks) {
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
}

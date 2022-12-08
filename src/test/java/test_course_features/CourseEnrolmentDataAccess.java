//package test_course_features;
//
//import entities.Course;
//import entities.StudentUser;
//import entities.Task;
//import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentDsGateway;
//import use_cases.course_features.course_enrolment_use_case.CourseTasksToStudentTodolistDsGateway;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class CourseEnrolmentDataAccess implements CourseEnrolmentDsGateway {
//    private final HashMap<String, Course> courseMap;
//    private final HashMap<String, StudentUser> studentMap;
//    private final HashMap<String, Task> tasksMap;
//
//    public CourseEnrolmentDataAccess(HashMap<String, Course> courseMap,
//                                     HashMap<String, StudentUser> studentMap,
//                                     HashMap<String, Task> tasksMap) {
//        this.courseMap = courseMap;
//        this.studentMap = studentMap;
//        this.tasksMap = tasksMap;
//    }
//
//    @Override
//    public boolean existsByCourseID(String courseIdentifier) {
//        return courseMap.containsKey(courseIdentifier);
//    }
//    @Override
//    public Course searchForCourse(String courseIdentifier) {
//        return courseMap.get(courseIdentifier);
//    }
//    @Override
//    public boolean existsStudentInCourse(String courseID, String studentIdentifier) {
//        return courseMap.get(courseID).getStudents().contains(studentIdentifier);
//    }
//    @Override
//    public void saveStudentToCourse(String studentID, String courseID) throws IOException {
//        courseMap.get(courseID).getStudents().add(studentID);
//    }
//    @Override
//    public ArrayList<String> courseTasks(Course requestModel) {
//        return requestModel.getTasks();
//    }
//
//    /** course tasks to student to do list gateway, too lazy to override */
//    public void addSaveTasksToTodolist(String studentID, ArrayList<String> courseTasks) throws IOException {
//        StudentUser username = studentMap.get(studentID);
//        username.getToDoList().addAll(courseTasks);
//    }
//    public void addCourseToStudent(String studentCourse, String studentID) throws IOException {
//        StudentUser courseInStudent = studentMap.get(studentID);
//        courseInStudent.getCourses().add(studentCourse);
//    }
//}

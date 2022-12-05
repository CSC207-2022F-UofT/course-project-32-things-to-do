package use_cases.course_features.course_enrolment_use_case;

// Use case layer

import entities.StudentUser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Gateway containing the following methods (override in FileUser)
 * addSaveTasksToTodolist: takes tasks with new student username-curated ids
 * and appends to Student's 'todolist' parameter
 */
public interface CourseTasksToStudentTodolistDsGateway {
//    public StudentUser searchForStudent(String studentIdentifier);
    public void addSaveTasksToTodolist(String studentID, ArrayList<String> courseTasks) throws IOException;
    public void addCourseToStudent(String studentCourse, String studentID) throws IOException;
}

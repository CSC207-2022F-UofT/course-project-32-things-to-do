package use_cases;

import entities.StudentUser;
import entities.Course;
import entities.Task;
import entities.TaskMap;

public class TaskDeletionUseCase {
    /**
     * Delete a StudentUser's task by moving it from their toDoList into their taskArchive
     * @param student - the student removing the Task
     * @param task - the Task being removed
     */
    public void deleteStudentTask(StudentUser student, Task task) {
        student.removeFromToDoList(task);
        student.addToArchive(task);
    }

    /**
     * Delete a Course's task by removing it from the Course's task list and the taskMap
     * @param course - the Course the Task is being removed from
     * @param task - the Task being removed
     */
    public void deleteCourseTask(Course course, Task task) {
        course.removeTask(task);
        TaskMap.removeTask(task);
    }
}

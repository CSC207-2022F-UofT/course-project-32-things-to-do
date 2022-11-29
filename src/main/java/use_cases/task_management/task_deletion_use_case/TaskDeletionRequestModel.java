<<<<<<<< HEAD:src/main/java/use_cases/task_management/task_deletion_use_case/TaskDeletionRequestModel.java
package use_cases.task_management.task_deletion_use_case;
========
package use_cases.task_deletion_use_case;
>>>>>>>> f84ff17 (at this point im not sure):src/main/java/use_cases/task_deletion_use_case/TaskDeletionRequestModel.java

import entities.Course;
import entities.StudentUser;
import entities.Task;

public class TaskDeletionRequestModel {
    private StudentUser student;
    private Course course;
    private Task task;

    // for student tasks
    public TaskDeletionRequestModel(StudentUser student, Task task) {
        this.student = student;
        this.task = task;
    }
    // for course tasks
    public TaskDeletionRequestModel(Course course, Task task) {
        this.course = course;
        this.task = task;
    }

    public StudentUser getStudent() {
        return this.student;
    }
    public Course getCourse() {
        return this.course;
    }
    public Task getTask() {
        return this.task;
    }
}

package use_cases.task_management.task_deletion_use_case;

import entities.Course;
import entities.StudentUser;
import entities.Task;

public class TaskDeletionRequestModel {
    //private String studentId;
    private StudentUser student;
    private String taskId;

    // for student tasks
    public TaskDeletionRequestModel(StudentUser student, String taskId) {
        this.student = student;
        this.taskId = taskId;
    }

    public StudentUser getStudent() {
        return this.student;
    }
    public String getTaskId() {
        return this.taskId;
    }
}

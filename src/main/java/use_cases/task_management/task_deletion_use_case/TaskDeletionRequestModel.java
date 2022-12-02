package use_cases.task_management.task_deletion_use_case;

import entities.StudentUser;

public class TaskDeletionRequestModel {
    //private String studentId;
    private final StudentUser student;
    private final String taskId;

    /**
     * A request model for Task deletion
     * @param student - the student whose Task is being deleted
     * @param taskId - the ID of the Task
     */
    public TaskDeletionRequestModel(StudentUser student, String taskId) {
        this.student = student;
        this.taskId = taskId;
    }

    // getters
    public StudentUser getStudent() {
        return this.student;
    }
    public String getTaskId() {
        return this.taskId;
    }
}

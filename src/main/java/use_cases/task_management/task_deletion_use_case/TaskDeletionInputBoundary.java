package use_cases.task_management.task_deletion_use_case;

public interface TaskDeletionInputBoundary {
    TaskDeletionResponseModel deleteStudentTask(TaskDeletionRequestModel requestModel);
    TaskDeletionResponseModel deleteCourseTask(TaskDeletionRequestModel requestModel);
}

package use_cases.task_management.task_deletion_use_case;

public interface TaskDeletionInputBoundary {
    TaskDeletionResponseModel delete(TaskDeletionRequestModel requestModel);
    //TaskDeletionResponseModel deleteCourseTask(TaskDeletionRequestModel requestModel);
}

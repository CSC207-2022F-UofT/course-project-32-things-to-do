package task_deletion_use_case;

import entities.Course;
import entities.StudentUser;
import entities.Task;

public interface TaskDeletionInputBoundary {
    TaskDeletionResponseModel deleteStudentTask(TaskDeletionRequestModel requestModel);
    TaskDeletionResponseModel deleteCourseTask(TaskDeletionRequestModel requestModel);
}

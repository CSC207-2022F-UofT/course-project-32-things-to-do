<<<<<<<< HEAD:src/main/java/use_cases/task_management/task_deletion_use_case/TaskDeletionInputBoundary.java
package use_cases.task_management.task_deletion_use_case;
========
package use_cases.task_deletion_use_case;
>>>>>>>> f84ff17 (at this point im not sure):src/main/java/use_cases/task_deletion_use_case/TaskDeletionInputBoundary.java

public interface TaskDeletionInputBoundary {
    TaskDeletionResponseModel deleteStudentTask(TaskDeletionRequestModel requestModel);
    TaskDeletionResponseModel deleteCourseTask(TaskDeletionRequestModel requestModel);
}

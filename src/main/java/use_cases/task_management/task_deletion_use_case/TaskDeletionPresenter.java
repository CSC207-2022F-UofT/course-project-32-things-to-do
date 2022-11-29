<<<<<<<< HEAD:src/main/java/use_cases/task_management/task_deletion_use_case/TaskDeletionPresenter.java
package use_cases.task_management.task_deletion_use_case;
========
package use_cases.task_deletion_use_case;
>>>>>>>> f84ff17 (at this point im not sure):src/main/java/use_cases/task_deletion_use_case/TaskDeletionPresenter.java

public interface TaskDeletionPresenter {
    TaskDeletionResponseModel prepareSuccessView(TaskDeletionResponseModel t);
    TaskDeletionResponseModel prepareFailureView(String error);
}

package screens.task_management.task_edit_delete_screens;

// import entities.Course;
import entities.StudentUser;
import entities.Task;
import use_cases.task_management.task_deletion_use_case.TaskDeletionRequestModel;
import use_cases.task_management.task_deletion_use_case.TaskDeletionResponseModel;
import use_cases.task_management.task_deletion_use_case.TaskDeletionInputBoundary;

public class TaskDeletionController {
    final TaskDeletionInputBoundary input;
    public TaskDeletionController(TaskDeletionInputBoundary input) {
        this.input = input;
    }
    public TaskDeletionResponseModel delete(StudentUser student, String taskId) {
        TaskDeletionRequestModel requestModel = new TaskDeletionRequestModel(student, taskId);

        return input.delete(requestModel);
    }
    /*
    public TaskDeletionResponseModel delete(Course course, Task task) {
        TaskDeletionRequestModel requestModel = new TaskDeletionRequestModel(course, task);

        return input.deleteCourseTask(requestModel);
    }
    */
}

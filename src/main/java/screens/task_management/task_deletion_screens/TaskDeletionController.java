package screens.task_management.task_deletion_screens;

import entities.Course;
import entities.StudentUser;
import entities.Task;
import use_cases.task_management.task_deletion_use_case.TaskDeletionInputBoundary;
import use_cases.task_management.task_deletion_use_case.TaskDeletionRequestModel;
import use_cases.task_management.task_deletion_use_case.TaskDeletionResponseModel;

public class TaskDeletionController {
    final TaskDeletionInputBoundary input;
    public TaskDeletionController(TaskDeletionInputBoundary input) {
        this.input = input;
    }
    public TaskDeletionResponseModel delete(StudentUser student, Task task) {
        TaskDeletionRequestModel requestModel = new TaskDeletionRequestModel(student, task);

        return input.deleteStudentTask(requestModel);
    }
    public TaskDeletionResponseModel delete(Course course, Task task) {
        TaskDeletionRequestModel requestModel = new TaskDeletionRequestModel(course, task);

        return input.deleteCourseTask(requestModel);
    }
}

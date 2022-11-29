package assignment_creation_screens;

import use_cases.task_creation_use_case.AssignmentCreationRequestModel;
import use_cases.task_creation_use_case.TaskCreationResponseModel;
import use_cases.task_creation_use_case.TaskCreationInputBoundary;
import use_cases.task_creation_use_case.TaskCreationRequestModel;

import java.time.LocalDateTime;

public class AssignmentCreationController {
    final TaskCreationInputBoundary input;
    public AssignmentCreationController(TaskCreationInputBoundary input) {
        this.input = input;
    }
    TaskCreationResponseModel create(String title, int priority, LocalDateTime dueDate, double weightage) {
        TaskCreationRequestModel requestModel = new AssignmentCreationRequestModel(title, priority, dueDate,
                weightage);
        return input.create(requestModel, "Assignment");
    }
}

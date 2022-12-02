package screens.task_management.task_creation_screens.assignment_creation_screens;

import use_cases.task_management.task_creation_use_case.AssignmentCreationRequestModel;
import use_cases.task_management.task_creation_use_case.TaskCreationResponseModel;
import use_cases.task_management.task_creation_use_case.TaskCreationInputBoundary;
import use_cases.task_management.task_creation_use_case.TaskCreationRequestModel;

import java.time.LocalDateTime;

public class AssignmentCreationController {
    final TaskCreationInputBoundary input;

    /**
     * A controller which interacts with the Task creation use case (specifically Assignment creation)
     * @param input - the input boundary for creating Assignments
     */
    public AssignmentCreationController(TaskCreationInputBoundary input) {
        this.input = input;
    }

    /**
     * Attempt to create an Assignment
     * @param title - the title of the Assignment
     * @param priority - the priority of the Assignment
     * @param dueDate - the Assignment's due date
     * @param weightage - the Assignment's weightage
     * @return - response model (the input boundary will indicate success/failure)
     */
    TaskCreationResponseModel create(String title, int priority, LocalDateTime dueDate, double weightage) {
        // create request model
        TaskCreationRequestModel requestModel = new AssignmentCreationRequestModel(title, priority, dueDate,
                weightage);
        // try to create Assignment
        return input.create(requestModel, "Assignment");
    }
}

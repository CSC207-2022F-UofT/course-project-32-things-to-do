package screens.task_management.task_edit_delete_screens.assignment_edit_delete_screens;

import use_cases.task_management.task_edit_use_case.AssignmentEditRequestModel;
import use_cases.task_management.task_edit_use_case.TaskEditInputBoundary;
import use_cases.task_management.task_edit_use_case.TaskEditRequestModel;
import use_cases.task_management.task_edit_use_case.TaskEditResponseModel;

import java.io.IOException;
import java.time.LocalDateTime;

public class AssignmentEditController {
    final TaskEditInputBoundary input;

    /**
     * A controller which interacts with the Task edit use case
     * @param input - input boundary for editing
     */
    public AssignmentEditController(TaskEditInputBoundary input) {
        this.input = input;
    }

    /**
     * Attempt to edit an Assignment
     * @param complete - whether the Assignment is complete
     * @param id - the ID of the Assignment (for retrieval purposes)
     * @param priority - the priority of the Assignment
     * @param dueDate - the Assignment's due date
     * @param weightage - the Assignment's weightage
     * @param timeNeeded - the time needed to complete the Assignment
     * @param timeSpent - the time spent working on the Assignment
     * @return - response model (input boundary will indicate success/failure)
     */
    public TaskEditResponseModel edit(boolean complete, String id, int priority, LocalDateTime dueDate, double weightage,
                                      double timeNeeded, double timeSpent) throws IOException {
        // create request model
        TaskEditRequestModel requestModel = new AssignmentEditRequestModel(
                id, complete, priority, dueDate, weightage, timeNeeded, timeSpent);

        // attempt to edit Assignment
        return input.edit(requestModel, "Assignment");
    }

}

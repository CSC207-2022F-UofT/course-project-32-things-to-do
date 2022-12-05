package screens.task_management.task_edit_delete_screens.test_edit_delete_screens;

import use_cases.task_management.task_edit_use_case.TaskEditInputBoundary;
import use_cases.task_management.task_edit_use_case.TaskEditRequestModel;
import use_cases.task_management.task_edit_use_case.TaskEditResponseModel;
import use_cases.task_management.task_edit_use_case.TestEditRequestModel;

import java.io.IOException;
import java.time.LocalDateTime;

public class TestEditController {
    final TaskEditInputBoundary input;

    /**
     * A controller for interacting with the Task edit use case (specifically for Tests)
     * @param input - the input boundary for Task editing
     */
    public TestEditController(TaskEditInputBoundary input) {
        this.input = input;
    }

    /**
     * Attempt to edit a Test
     * @param complete - whether the Test is complete
     * @param id - the ID of the Test
     * @param priority - the priority of the Test
     * @param startTime - the Test's start time
     * @param endTime - the Test's end time
     * @param weightage - the Test's weightage
     * @param timeNeeded - the time needed to study
     * @param timeSpent - the time spent studying
     * @return - response model (input boundary will indicate success/failure)
     */
    public TaskEditResponseModel edit(boolean complete, String id, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                      double weightage, double timeNeeded, double timeSpent) throws IOException {
        // create request model
        TaskEditRequestModel requestModel = new TestEditRequestModel(id, complete, priority, startTime, endTime, weightage,
                timeNeeded, timeSpent);
        // try to edit Test
        return input.edit(requestModel, "Test");
    }

}

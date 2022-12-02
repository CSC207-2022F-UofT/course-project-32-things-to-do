package screens.task_management.task_edit_delete_screens.event_edit_delete_screens;

import use_cases.task_management.task_edit_use_case.EventEditRequestModel;
import use_cases.task_management.task_edit_use_case.TaskEditInputBoundary;
import use_cases.task_management.task_edit_use_case.TaskEditRequestModel;
import use_cases.task_management.task_edit_use_case.TaskEditResponseModel;

import java.time.LocalDateTime;

public class EventEditController {
    final TaskEditInputBoundary input;

    /**
     * A controller for interacting with the Task edit use case
     * @param input - the input boundary for editing
     */
    public EventEditController(TaskEditInputBoundary input) {
        this.input = input;
    }

    /**
     * Attempt to edit an Event
     * @param complete - whether the Event is complete
     * @param id - the ID of the Event (for retrieval purposes)
     * @param priority - the priority of the Event
     * @param startTime - the Event's start time
     * @param endTime - the Event's end time
     * @param recurring - whether the event is recurring
     * @param frequency - the frequency at which the event is recurring, if it is
     * @return - response model (input boundary will indicate success/failure)
     */
    public TaskEditResponseModel edit(boolean complete, String id, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                      boolean recurring, String frequency) {
        // create request model
        TaskEditRequestModel requestModel = new EventEditRequestModel(id, complete, priority, startTime, endTime, recurring, frequency);
        // try to edit Test
        return input.edit(requestModel, "Event");
    }

}

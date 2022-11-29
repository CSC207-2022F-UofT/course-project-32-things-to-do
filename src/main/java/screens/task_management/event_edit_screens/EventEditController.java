package screens.task_management.event_edit_screens;

import entities.Event;
import use_cases.task_management.task_edit_use_case.EventEditRequestModel;
import use_cases.task_management.task_edit_use_case.TaskEditInputBoundary;
import use_cases.task_management.task_edit_use_case.TaskEditRequestModel;
import use_cases.task_management.task_edit_use_case.TaskEditResponseModel;

import java.time.LocalDateTime;

public class EventEditController {
    final TaskEditInputBoundary input;
    public EventEditController(TaskEditInputBoundary input) {
        this.input = input;
    }

    public TaskEditResponseModel edit(Event event, String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                      boolean recurring, String frequency) {
        TaskEditRequestModel requestModel = new EventEditRequestModel(event, title, priority, startTime, endTime, recurring, frequency);
        return input.edit(requestModel, "Event");
    }

}

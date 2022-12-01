package screens.task_management.task_edit_delete_screens.event_edit_delete_screens;

import entities.Event;
import entities.TaskMap;
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

    public TaskEditResponseModel edit(boolean complete, String id, String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                      boolean recurring, String frequency) {
        TaskEditRequestModel requestModel = new EventEditRequestModel(id, complete, title, priority, startTime, endTime, recurring, frequency);
        return input.edit(requestModel, "Event");
    }

}

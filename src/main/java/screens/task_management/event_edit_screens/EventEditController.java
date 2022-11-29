package screens.task_management.event_edit_screens;

import entities.Event;
import use_cases.task_management.event_edit_use_case.EventEditInputBoundary;
import use_cases.task_management.event_edit_use_case.EventEditRequestModel;
import use_cases.task_management.event_edit_use_case.EventEditResponseModel;

import java.time.LocalDateTime;

public class EventEditController {
    final EventEditInputBoundary input;
    public EventEditController(EventEditInputBoundary input) {
        this.input = input;
    }

    public EventEditResponseModel edit(Event event, String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                       boolean recurring, String frequency) {
        EventEditRequestModel requestModel = new EventEditRequestModel(event, title, priority, startTime, endTime, recurring, frequency);
        return input.edit(requestModel);
    }

}

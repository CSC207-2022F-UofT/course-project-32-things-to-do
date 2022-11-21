package event_edit_screens;

import entities.Event;
import event_edit_use_case.EventEditInputBoundary;
import event_edit_use_case.EventEditRequestModel;
import event_edit_use_case.EventEditResponseModel;

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

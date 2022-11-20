package screens;

import event_creation_use_case.EventCreationInputBoundary;
import event_creation_use_case.EventCreationRequestModel;
import event_creation_use_case.EventCreationResponseModel;

import java.time.LocalDateTime;

public class EventCreationController {
    final EventCreationInputBoundary input;
    public EventCreationController(EventCreationInputBoundary eventGateway) {
        this.input = eventGateway;
    }
    EventCreationResponseModel create(String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                      boolean recurring, String frequency) {
        EventCreationRequestModel requestModel = new EventCreationRequestModel(title, priority, startTime, endTime,
                recurring, frequency);
        return input.create(requestModel);
    }
}

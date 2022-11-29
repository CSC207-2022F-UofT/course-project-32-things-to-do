package screens.task_management.event_creation_screens;

import use_cases.task_management.event_creation_use_case.EventCreationInputBoundary;
import use_cases.task_management.event_creation_use_case.EventCreationRequestModel;
import use_cases.task_management.event_creation_use_case.EventCreationResponseModel;

import java.time.LocalDateTime;

public class EventCreationController {
    final EventCreationInputBoundary input;
    public EventCreationController(EventCreationInputBoundary input) {
        this.input = input;
    }
    EventCreationResponseModel create(String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                      boolean recurring, String frequency) {
        EventCreationRequestModel requestModel = new EventCreationRequestModel(title, priority, startTime, endTime,
                recurring, frequency);
        return input.create(requestModel);
    }
}

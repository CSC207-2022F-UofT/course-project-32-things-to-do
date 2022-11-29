package event_creation_screens;

import task_creation_use_case.EventCreationRequestModel;
import task_creation_use_case.TaskCreationInputBoundary;
import task_creation_use_case.TaskCreationResponseModel;

import java.time.LocalDateTime;

public class EventCreationController {
    final TaskCreationInputBoundary input;
    public EventCreationController(TaskCreationInputBoundary input) {
        this.input = input;
    }
    TaskCreationResponseModel create(String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                     boolean recurring, String frequency) {
        EventCreationRequestModel requestModel = new EventCreationRequestModel(title, priority, startTime, endTime,
                recurring, frequency);
        return input.create(requestModel, "Event");
    }
}

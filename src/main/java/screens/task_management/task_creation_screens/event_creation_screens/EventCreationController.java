package screens.task_management.task_creation_screens.event_creation_screens;

import use_cases.task_management.task_creation_use_case.TaskCreationInputBoundary;
import use_cases.task_management.task_creation_use_case.TaskCreationResponseModel;
import use_cases.task_management.task_creation_use_case.EventCreationRequestModel;

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

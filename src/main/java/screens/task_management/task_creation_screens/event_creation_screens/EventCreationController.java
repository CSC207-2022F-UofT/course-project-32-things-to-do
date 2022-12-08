package screens.task_management.task_creation_screens.event_creation_screens;

import use_cases.task_management.task_creation_use_case.*;

import java.time.LocalDateTime;

public class EventCreationController {
    final TaskCreationInputBoundary input;

    /**
     * A controller for interacting with the Task creation use case (specifically Event creation)
     * @param input - the input boundary for creating Events
     */
    public EventCreationController(TaskCreationInputBoundary input) {
        this.input = input;
    }

    /**
     * Attempt to create an Event
     *
     * @param title     - the title of the Event
     * @param priority  - the priority of the Event
     * @param startTime - the Event's start time
     * @param endTime   - the Event's end time
     * @param recurring - whether the Event is recurring
     * @param frequency - if the Event is recurring--its frequency
     */
    void create(String title, int priority, LocalDateTime startTime, LocalDateTime endTime,
                boolean recurring, String frequency) {
        // create the request model
        EventCreationRequestModel requestModel = new EventCreationRequestModel(title, priority, startTime, endTime,
                recurring, frequency);
        // try to create the Event
        input.create(requestModel, "Event");
    }
}

package use_cases.task_management.task_edit_use_case;

import entities.Event;

import java.time.LocalDateTime;

public class EventInfoRetriever implements EventDisplayer {
    private final Event event;
    /**
     * An object that retrieves the info of an Event to be displayed in edit screens
     * @param event - the Event whose info is being displayed
     */
    public EventInfoRetriever(Event event) {
        this.event = event;
    }
    @Override
    public String getTitle() {
        return event.getTitle();
    }

    @Override
    public String getId() {
        return event.getId();
    }

    @Override
    public int getPriority() {
        return event.getPriority();
    }

    @Override
    public LocalDateTime getStartTime() {
        return event.getTimeBlock()[0];
    }

    @Override
    public LocalDateTime getEndTime() {
        return event.getTimeBlock()[1];
    }

    @Override
    public boolean getRecurring() {
        return event.getRecurring();
    }

    @Override
    public String getFrequency() {
        return event.getFrequency();
    }
}

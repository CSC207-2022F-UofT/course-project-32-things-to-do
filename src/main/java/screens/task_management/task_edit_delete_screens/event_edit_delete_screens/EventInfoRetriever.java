package screens.task_management.task_edit_delete_screens.event_edit_delete_screens;

import entities.Event;
import entities.TaskMap;
import use_cases.task_management.task_edit_use_case.EventDisplayer;

import java.time.LocalDateTime;

public class EventInfoRetriever implements EventDisplayer {
    private final Event event;
    private final String id;
    /**
     * An object that retrieves the info of an Event to be displayed in edit screens
     * @param id - ID of the Event whose info is being displayed
     */
    public EventInfoRetriever(String id) {
        this.event = (Event) TaskMap.findTask(id);
        this.id = id;
    }

    // Event getters
    @Override
    public String getTitle() {
        return event.getTitle();
    }

    @Override
    public String getId() {
        return this.id;
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

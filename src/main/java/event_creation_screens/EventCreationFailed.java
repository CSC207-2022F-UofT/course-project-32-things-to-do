package event_creation_screens;

public class EventCreationFailed extends RuntimeException {
    public EventCreationFailed(String error) {
        super(error);
    }
}

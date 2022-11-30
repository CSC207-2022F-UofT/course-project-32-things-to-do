package screens.calendar_scheduler;

public class ScheduleFailed extends RuntimeException {

    public ScheduleFailed(String error) {
        super(error);
    }
}

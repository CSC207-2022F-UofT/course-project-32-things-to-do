package use_cases.task_management.task_edit_use_case;

import java.time.LocalDateTime;

// for retrieving info about an Event
public interface EventDisplayer {
    String getTitle();
    String getId();
    int getPriority();
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
    boolean getRecurring();
    String getFrequency();
}

package use_cases.task_management.task_edit_use_case;

import java.time.LocalDateTime;

public interface EventDisplayer {
    String getTitle();
    String getId();
    int getPriority();
    String getDate();
    String getStartTime();
    String getEndTime();
    boolean getRecurring();
    String getFrequency();
}

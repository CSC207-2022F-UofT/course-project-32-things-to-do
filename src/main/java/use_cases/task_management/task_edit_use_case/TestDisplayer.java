package use_cases.task_management.task_edit_use_case;

import java.time.LocalDateTime;

// for retrieving information about a Test
public interface TestDisplayer {
    String getTitle();
    String getId();
    int getPriority();
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
    double getWeightage();
    double getTimeNeeded();
    double getTimeSpent();
}

package use_cases.task_management.task_edit_use_case;

import java.time.LocalDateTime;

public interface AssignmentDisplayer {
    String getTitle();
    String getId();
    int getPriority();
    LocalDateTime getDueDate();
    double getWeightage();
    double getTimeNeeded();
    double getTimeSpent();
}

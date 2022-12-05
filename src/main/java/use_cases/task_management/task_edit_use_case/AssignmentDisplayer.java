package use_cases.task_management.task_edit_use_case;

import java.time.LocalDateTime;

// for retrieving info about an Assignment
public interface AssignmentDisplayer {
    String getTitle();
    String getId();
    int getPriority();
    LocalDateTime getDueDate();
    double getWeightage();
    double getTimeNeeded();
    double getTimeSpent();
}

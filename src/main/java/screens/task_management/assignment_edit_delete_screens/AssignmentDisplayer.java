package screens.task_management.assignment_edit_delete_screens;

import java.time.LocalDateTime;

public interface AssignmentDisplayer {
    String getTitle();
    String getId();
    int getPriority();
    LocalDateTime getDueDate();
    double getWeightage();
}

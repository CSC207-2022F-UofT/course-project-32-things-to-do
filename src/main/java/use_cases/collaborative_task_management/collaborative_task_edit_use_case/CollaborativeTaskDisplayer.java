package use_cases.collaborative_task_management.collaborative_task_edit_use_case;

import entities.StudentUser;
import java.time.LocalDateTime;
import java.util.ArrayList;

// for retrieving information about a Collaborative Task
public interface CollaborativeTaskDisplayer {
    String getTitle();
    String getId();
    int getPriority();
    boolean isRecurring();
    String getFrequency();
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
    LocalDateTime getDeadline();
    StudentUser getLeader();
}

package use_cases.collaborative_task_management.collaborative_task_creation_use_case;

import entities.CollaborativeTask;
import entities.StudentUser;

import java.time.LocalDateTime;

public class CollaborativeTaskCreationResponseModel {
    final String title;
    final LocalDateTime startTime;
    final LocalDateTime endTime;
    final LocalDateTime deadline;
    final StudentUser leader;

    public CollaborativeTaskCreationResponseModel(String title, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime deadline, StudentUser leader) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deadline = deadline;
        this.leader = leader;
    }
}
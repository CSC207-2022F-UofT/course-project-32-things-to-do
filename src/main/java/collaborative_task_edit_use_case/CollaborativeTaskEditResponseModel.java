package collaborative_task_edit_use_case;

import entities.StudentUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CollaborativeTaskEditResponseModel {
    final String title;
    final LocalDateTime startTime;
    final LocalDateTime endTime;
    final LocalDateTime deadline;
    final ArrayList<StudentUser> teammates;
    final StudentUser leader;

    public CollaborativeTaskEditResponseModel(String title, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime deadline, ArrayList<StudentUser> teammates, StudentUser leader) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deadline = deadline;
        this.teammates = teammates;
        this.leader = leader;
    }
}
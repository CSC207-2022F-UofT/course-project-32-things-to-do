package use_case;

import entities.CollaborativeTask;
import entities.StudentUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CollaborativeSchedulingRequestModel {

    private final CollaborativeTask task;

    private final StudentUser user;


    public CollaborativeSchedulingRequestModel(CollaborativeTask task, StudentUser user) {
        this.task = task;
        this.user = user;
    }

    public CollaborativeTask getTask() {
        return task;
    }

    public StudentUser getUser() {
        return user;
    }


}

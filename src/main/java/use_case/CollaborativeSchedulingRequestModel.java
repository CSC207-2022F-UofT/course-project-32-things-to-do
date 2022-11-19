package use_case;

import entities.CollaborativeTask;
import entities.TaskMap;
import entities.StudentUser;
import entities.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CollaborativeSchedulingRequestModel {

    private final CollaborativeTask task;

    private final TaskMap allTasks;

    // private final StudentUser user;


    public CollaborativeSchedulingRequestModel(CollaborativeTask task, TaskMap allTasks) {
        this.task = task;
        this.allTasks = allTasks;
    }
    public TaskMap getAllTasks(){
        return allTasks;
    }
    public CollaborativeTask getTask() {
        return task;
    }



}

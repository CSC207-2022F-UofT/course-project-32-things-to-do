package use_case_collaborative_scheduling;

import entities.CollaborativeTask;
import entities.TaskMap;

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

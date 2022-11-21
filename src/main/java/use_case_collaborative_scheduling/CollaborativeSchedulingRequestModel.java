package use_case_collaborative_scheduling;

import entities.TaskMap;

/*
 Notes:
 - requests what is needed for its input data (what person in front of computer enters)
 - do NOT depend on anything NOR have any references to Entity objects: violates SRP
 */


public class CollaborativeSchedulingRequestModel {

//    private final CollaborativeTask task;
//    private final TaskMap allTasks;

    // private final StudentUser user;

    private final String taskTitle;
    private final TaskMap taskMap;


    public CollaborativeSchedulingRequestModel(String taskTitle, TaskMap taskMap) {
        this.taskTitle = taskTitle;
        this.taskMap = taskMap;
    }
    public String getTaskTitle() {
        return this.taskTitle;
    }
    public TaskMap getTaskMap() {
        return this.taskMap;
    }





}

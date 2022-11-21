package screens;

import use_case_collaborative_scheduling.CollaborativeSchedulingRequestModel;
import use_case_collaborative_scheduling.CollaborativeSchedulingResponseModel;
import use_case_collaborative_scheduling.CollaborativeSchedulingInputBoundary;
import entities.TaskMap;

public class CollaborativeSchedulingController {

    // controller creates input data (request model) based on information from the view then calls the input boundary
    // method (implemented by the interactor), passing the Input Data object as a parameter

    // assume there is an internal class of task ids mapped to the task object itself

    // for getting the task object associated with the task id, when the program loads we're going to have a gateway
    // that loads every task from a file and creates those objects, and we will have a method that returns the task
    // based on like type, etc.
    // figure that out when we so the serialization
    // main controller will load and then inject into other controllers that need it

    // make a method to get the task given the name of a task
    final CollaborativeSchedulingInputBoundary scheduleInput;

    private final TaskMap allTasks;

    public CollaborativeSchedulingController(CollaborativeSchedulingInputBoundary scheduleInput, TaskMap allTasks) {
        this.scheduleInput = scheduleInput;
        this.allTasks = allTasks;
    }
    CollaborativeSchedulingResponseModel findTimes(String title){
        CollaborativeSchedulingRequestModel inputData = new CollaborativeSchedulingRequestModel(title, this.allTasks);
        return scheduleInput.schedule(inputData, allTasks);
    }
}

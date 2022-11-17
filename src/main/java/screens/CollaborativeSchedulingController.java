package screens;

import entities.CollaborativeTask;
import entities.StudentUser;
import use_case.CollaborativeSchedulingRequestModel;
import use_case.CollaborativeSchedulingResponseModel;
import use_case.CollaborativeSchedulingInputBoundary;
import use_case.CollaborativeScheduling;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    public CollaborativeSchedulingController(CollaborativeSchedulingInputBoundary scheduleInput) {
        this.scheduleInput = scheduleInput;
    }
    public CollaborativeSchedulingResponseModel findTimes(CollaborativeTask task, StudentUser user){
        CollaborativeSchedulingRequestModel inputData = new CollaborativeSchedulingRequestModel(task, user);
        return scheduleInput.schedule(inputData);
    }
}

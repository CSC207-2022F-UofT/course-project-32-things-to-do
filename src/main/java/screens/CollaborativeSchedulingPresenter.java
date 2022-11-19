package screens;

import use_case_collaborative_scheduling.CollaborativeSchedulingOutputBoundary;
import use_case_collaborative_scheduling.CollaborativeSchedulingResponseModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CollaborativeSchedulingPresenter implements CollaborativeSchedulingOutputBoundary{

    // the presenter has reference to the view, and you call the view stuff from here
    // take result of a use case and update the user interface
    // turn output data into date time formatter to make it look nicer since outputData is an arraylist of arraylists

    // How does the presenter update the view when it's not allowed to mention it?
    // dependency inversion
    // presenter says I need to update the view with this information and so what I'm going to do is create an interface
    // with a method that takes in that information in the parameters
    // the view object is going to implement that interface and inject itself into the use case
    // often the view knows the controller

    // action performed method in view looks in that text field and gets the string out and then call a method in the controller
    // that is responsible for that use case
    // controller takes in the string in the parameter in the method that the view called
    // controller knows which use case interactor can do this

    private final CollaborativeSchedulingResponseModel outputData;

    public CollaborativeSchedulingPresenter(CollaborativeSchedulingResponseModel outputData){
        this.outputData = outputData;
    }

    public ArrayList<String> convertToString(){

        // initialize empty ArrayList
        ArrayList<String> output = new ArrayList<>();

        for (ArrayList<LocalDateTime> timeBlock : outputData.getAvailableTimes()) {
            LocalDateTime startTime = timeBlock.get(0);
            LocalDateTime endTime = timeBlock.get(1);

            // specifying the format of what I want returned for the date time
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            String formattedStartTime = startTime.format(format);
            String formattedEndTime = endTime.format(format);

            String possibleTime = formattedStartTime + " to " + formattedEndTime;
            output.add(possibleTime);
        }
        return output;
    }

    @Override
    public CollaborativeSchedulingResponseModel prepareAvailableTimes(CollaborativeSchedulingResponseModel outputData){
        return outputData;
    }

    @Override
    public CollaborativeSchedulingResponseModel prepareFailView(String error){
        throw new SchedulingTimesFailed(error);
    }


}

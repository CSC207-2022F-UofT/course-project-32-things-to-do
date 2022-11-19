package screens;

import entities.*;
import screens.CollaborativeSchedulingPresenter;
import use_case.*;

import java.util.ArrayList;

public interface CollaborativeSchedulingPresenterInterface {

    // How does the presenter update the view when it's not allowed to mention it?
    // dependency inversion
    // presenter says I need to update the view with this information and so what I'm going to do is create an interface
    // with a method that takes in that information in the parameters
    // the view object is going to implement that interface and inject itself into the use case
    // often the view knows the controller

    ArrayList<String> present(CollaborativeSchedulingPresenter output);

}

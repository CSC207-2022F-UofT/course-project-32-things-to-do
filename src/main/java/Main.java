import course_creation_use_case.*;
import entities.*;
import event_creation_screens.*;
import event_creation_use_case.*;
import login_usecase.*;
import progress_tracker_use_case.*;
import scheduling_ct_screens.*;
import scheduling_ct_use_case.ScheduleCTInputBoundary;
import scheduling_ct_use_case.ScheduleCTInteractor;
import scheduling_ct_use_case.ScheduleCTOutputBoundary;
import schedule_conflict_use_case.ScheduleConflictPresenter;
import scheduler_use_case.SchedulerPresenter;
import screens.*;
import user_register_usecase.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        // Build the main program window
        JFrame application = new JFrame("32 Things To Do");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        // Get objects from database
        HashMap<String, Task> allTasks = new HashMap<>();
        HashMap<String, User> allUsers = new HashMap<>();
        HashMap<String, Course> allCourses = new HashMap<>();

        // Create the components for injection into the use cases
        UserRegGateway regUser = new InMemoryUser();
        UserRegPresenter userPresenter = new UserRegResponseFormatter();
        UserRegInputBoundary userInteractor = new UserRegInteractor(regUser, userPresenter);
        UserRegController userRegisterController = new UserRegController(userInteractor);

        User user = ((UserRegInteractor) userInteractor).getUser();

        SchedulerPresenter schedulerPresenter = new SchedulerResponseFormatter();
        ScheduleConflictPresenter scheduleConflictPresenter = new ScheduleConflictResponseFormatter();

        EventCreationPresenter eventPresenter = new EventCreationResponseFormatter();
        EventCreationInputBoundary eventInteractor = new EventCreationInteractor(eventPresenter, (StudentUser) user,
                schedulerPresenter, scheduleConflictPresenter);
        EventCreationController eventCreationController = new EventCreationController(eventInteractor);

        ProgressTrackerOutputBoundary trackerPresenter = new ProgressTrackerPresenter();
        ProgressTrackerInputBoundary trackerInteractor = new ProgressTrackerInteractor (trackerPresenter);
        ProgressTrackerController trackerController = new ProgressTrackerController(trackerInteractor, user, "", allTasks, allUsers, allCourses);

        CourseCreationDsGateway course;
        try {
            course = new FileCourse("./courses.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        CourseCreationPresenter presenter = new CourseCreationResponseFormatter();
        CourseMap courseMap = new CourseMap();
        CourseCreationInputBoundary interactor = new CourseCreationInteractor(course, presenter, courseMap);
        CourseCreationController courseCreationController = new CourseCreationController(interactor);

        // Build the GUI
        EventCreationScreen taskScreen = new EventCreationScreen(eventCreationController, screens, cardLayout);
        screens.add("toDoList", taskScreen);

        CalendarScreen calendarScreen = new CalendarScreen((StudentUser) user, allTasks, screens, cardLayout);
        screens.add("calendar", calendarScreen);

        ProgressTrackerScreen progressTrackerScreen = new ProgressTrackerScreen(trackerController);
        screens.add("tracker", progressTrackerScreen);

        CourseCreationScreen courseCreationScreen = new CourseCreationScreen(courseCreationController, screens, cardLayout);
        screens.add("course", courseCreationScreen);

        MainScreen mainScreen = new MainScreen(screens, cardLayout);
        screens.add("main", mainScreen);

        RegisterScreen registerScreen = new RegisterScreen(userRegisterController, cardLayout, screens);
        screens.add("register", registerScreen);

//        LoginScreen loginScreen = new LoginScreen(loginController);
//        screens.add("login", loginScreen);

        WelcomeScreen welcomeScreen = new WelcomeScreen(cardLayout, screens);
        screens.add("welcome", welcomeScreen);

        cardLayout.show(screens, "welcome");
        application.pack();
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

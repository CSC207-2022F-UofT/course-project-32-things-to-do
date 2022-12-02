import entities.*;
import screens.*;
import screens.calendar_scheduler.*;
import screens.course_features.*;
import screens.course_progress.*;
import screens.login_registration.*;
import screens.task_management.event_creation_screens.*;
import use_cases.course_features.course_creation_use_case.*;
import use_cases.course_features.course_enrolment_use_case.*;
import use_cases.course_tracker.progress_tracker_use_case.*;
import use_cases.login_registration.user_register_usecase.*;
import use_cases.task_management.event_creation_use_case.*;


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

        EventCreationPresenter eventPresenter = new EventCreationResponseFormatter();
//        EventCreationInputBoundary eventInteractor = new EventCreationInteractor(eventPresenter, (StudentUser) user);
//        EventCreationController eventCreationController = new EventCreationController(eventInteractor);

        ProgressTrackerOutputBoundary trackerPresenter = new ProgressTrackerPresenter();
        ProgressTrackerInputBoundary trackerInteractor = new ProgressTrackerInteractor (trackerPresenter);
        ProgressTrackerController trackerController = new ProgressTrackerController(trackerInteractor, user, "", allTasks, allUsers, allCourses);

        CourseCreationDsGateway createCourse;
        try {
            createCourse = new FileCourse("src/main/java/data/courses.ser");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Could not create file.");
        }
        CourseCreationOutputBoundary coursePresenter = new CourseCreationPresenter();
        CourseCreationInputBoundary courseInteractor = new CourseCreationInteractor(createCourse, coursePresenter);
        CourseCreationController courseController = new CourseCreationController(courseInteractor);

        CourseEnrolmentDsGateway enrolCourse;
        try {
            // for testing:
            // course = new InMemoryCourse();
            enrolCourse = new FileCourse("src/main/java/data/courses.ser");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Could not create file.");
        }

        CourseEnrolmentOutputBoundary enrolPresenter = new CourseEnrolmentPresenter();
        CourseEnrolmentInputBoundary enrolInteractor = new CourseEnrolmentInteractor(enrolCourse, enrolPresenter);
        CourseEnrolmentController enrolController = new CourseEnrolmentController(enrolInteractor);

        // Build the GUI
//        EventCreationScreen taskScreen = new EventCreationScreen(eventCreationController, screens, cardLayout);
//        screens.add("toDoList", taskScreen);

        CalendarScreen calendarScreen = new CalendarScreen((StudentUser) user, allTasks, screens, cardLayout);
        screens.add("calendar", calendarScreen);

        ProgressTrackerScreen progressTrackerScreen = new ProgressTrackerScreen(trackerController);
        screens.add("tracker", progressTrackerScreen);

        CourseCreationScreen courseCreationScreen = new CourseCreationScreen(courseController, screens, cardLayout);
        screens.add("createcourse", courseCreationScreen);

        CourseEnrolmentScreen courseEnrolmentScreen = new CourseEnrolmentScreen(enrolController, screens, cardLayout);
        screens.add("enrolcourse", courseEnrolmentScreen);

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

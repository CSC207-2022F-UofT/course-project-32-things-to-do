import entities.*;
import screens.*;
import screens.calendar_scheduler.*;
import screens.course_progress.*;
import screens.courses_features.*;
import screens.login_registration.*;
import screens.task_management.event_creation_screens.*;
import use_cases.course_features.course_creation_use_case.*;
import use_cases.course_tracker.progress_tracker_use_case.*;
import screens.collaborative_task_scheduling.*;
import use_cases.collaborative_task_scheduling.scheduling_ct_use_case.*;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictPresenter;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerPresenter;
import use_cases.login_registration.login_usecase.LoginGateway;
import use_cases.login_registration.login_usecase.LoginInputBoundary;
import use_cases.login_registration.login_usecase.LoginInteractor;
import use_cases.login_registration.login_usecase.LoginPresenter;
import use_cases.login_registration.logout_usecase.LogoutGateway;
import use_cases.login_registration.logout_usecase.LogoutInputBoundary;
import use_cases.login_registration.logout_usecase.LogoutInteractor;
import use_cases.login_registration.logout_usecase.LogoutPresenter;
import use_cases.login_registration.user_register_usecase.*;
import use_cases.task_management.event_creation_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

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
        UserRegGateway regUser = new FileUser("src/main/java/data/users.ser");
        UserFactory fac = new GeneralUserFactory();
        UserRegPresenter userPresenter = new UserRegResponseFormatter();
        UserRegInputBoundary userInteractor = new UserRegInteractor(regUser, userPresenter, fac);
        UserRegController userRegisterController = new UserRegController(userInteractor);

        // Adding in login use case
        LoginGateway loginUser = new FileUser("src/main/java/data/users.ser");
        LoginPresenter loginPresenter = new LoginResponseFormatter();
        LoginInputBoundary loginInteractor = new LoginInteractor(loginUser, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);
        //

        // initialize User based on whether they log in or register
        // if you don't register, then you are logging in:
        User user;
        if ((((UserRegInteractor) userInteractor).getUser() instanceof StudentUser) |
                (((UserRegInteractor) userInteractor).getUser() instanceof InstructorUser)) {
            user = ((UserRegInteractor) userInteractor).getUser();
        } else {
            user = ((LoginInteractor) loginInteractor).getUser();
        }


        SchedulerPresenter schedulerPresenter = new SchedulerResponseFormatter();
        ScheduleConflictPresenter scheduleConflictPresenter = new ScheduleConflictResponseFormatter();

        EventCreationPresenter eventPresenter = new EventCreationResponseFormatter();
        EventCreationInputBoundary eventInteractor = new EventCreationInteractor(eventPresenter, (StudentUser) user,
                schedulerPresenter, scheduleConflictPresenter);
        EventCreationController eventCreationController = new EventCreationController(eventInteractor);

        ProgressTrackerOutputBoundary trackerPresenter = new ProgressTrackerPresenter();
        ProgressTrackerInputBoundary trackerInteractor = new ProgressTrackerInteractor(trackerPresenter);
        ProgressTrackerController trackerController = new ProgressTrackerController(trackerInteractor, user, "", allTasks, allUsers, allCourses);

        ScheduleCTViewInterface presentOutputInterface = new ScheduleCTView(cardLayout, screens);
        ScheduleCTOutputBoundary scheduleCTOutputBoundary = new ScheduleCTPresenter(presentOutputInterface);
        ScheduleCTInputBoundary scheduleCTInputBoundary = new ScheduleCTInteractor(scheduleCTOutputBoundary);
        ScheduleCTController scheduleCTController = new ScheduleCTController(scheduleCTInputBoundary, allTasks, (StudentUser) user);

        CourseCreationDsGateway course;
        try {
            course = new FileCourse("./src/main/java/data/courses.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        CourseCreationPresenter presenter = new CourseCreationResponseFormatter();
        CourseMap courseMap = new CourseMap();
        CourseCreationInputBoundary interactor = new CourseCreationInteractor(course, presenter, courseMap);
        CourseCreationController courseCreationController = new CourseCreationController(interactor);

        // Adding in logout use case
        LogoutGateway logoutUser = new FileUser("src/main/java/data/users.ser");
        LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutUser);
        LogoutController logoutController = new LogoutController(logoutInteractor);
        //

        // Build the GUI
        EventCreationScreen taskScreen = new EventCreationScreen(eventCreationController, screens, cardLayout);
        screens.add("toDoList", taskScreen);

        CalendarScreen calendarScreen = new CalendarScreen((StudentUser) user, allTasks, screens, cardLayout);
        screens.add("calendar", calendarScreen);

        ScheduleCTScreen scheduleCTScreen = new ScheduleCTScreen(scheduleCTController, screens, cardLayout);
        screens.add("scheduleCT", scheduleCTScreen);

        ProgressTrackerScreen progressTrackerScreen = new ProgressTrackerScreen(trackerController);
        screens.add("tracker", progressTrackerScreen);

        CourseCreationScreen courseCreationScreen = new CourseCreationScreen(courseCreationController, screens, cardLayout);
        screens.add("course", courseCreationScreen);

        StudentMainScreen studentMainScreen = new StudentMainScreen(screens, cardLayout, logoutController);
        screens.add("StudentMain", studentMainScreen);

        RegisterScreen registerScreen = new RegisterScreen(userRegisterController, cardLayout, screens);
        screens.add("register", registerScreen);

        LoginScreen loginScreen = new LoginScreen(loginController, cardLayout, screens);
        screens.add("login", loginScreen);

        InstructorMainScreen instructorMainScreen = new InstructorMainScreen(screens, cardLayout, logoutController);
        screens.add("InstructorMain", instructorMainScreen);

        WelcomeScreen welcomeScreen = new WelcomeScreen(cardLayout, screens);
        screens.add("welcome", welcomeScreen);

        cardLayout.show(screens, "welcome");
        application.pack();
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

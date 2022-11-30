import entities.*;
import screens.*;
import screens.calendar_scheduler.*;
import screens.course_progress.*;
import screens.courses_features.*;
import screens.login_registration.*;
import screens.task_management.ChooseTaskCreateScreen;
import screens.task_management.assignment_creation_screens.AssignmentCreationController;
import screens.task_management.assignment_creation_screens.AssignmentCreationScreen;
import screens.task_management.event_creation_screens.*;
import screens.task_management.test_creation_screens.TestCreationController;
import screens.task_management.test_creation_screens.TestCreationScreen;
import use_cases.course_features.course_creation_use_case.*;
import use_cases.course_tracker.progress_tracker_use_case.*;
import screens.collaborative_task_scheduling.*;
import use_cases.collaborative_task_scheduling.scheduling_ct_use_case.*;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictPresenter;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerPresenter;
import use_cases.login_registration.user_register_usecase.*;
import use_cases.task_management.task_creation_use_case.*;

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

        TaskCreationPresenter taskPresenter = new EventCreationResponseFormatter();
        TaskCreationInputBoundary taskInteractor = new TaskCreationInteractor(taskPresenter, (StudentUser) user, "none",
                schedulerPresenter, scheduleConflictPresenter);
        EventCreationController eventCreationController = new EventCreationController(taskInteractor);
        AssignmentCreationController assignmentCreationController = new AssignmentCreationController(taskInteractor);
        TestCreationController testCreationController = new TestCreationController(taskInteractor);

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

        // Build the GUI
        ChooseTaskCreateScreen chooseTask = new ChooseTaskCreateScreen(screens, cardLayout);
        screens.add("toDoList", chooseTask);

        EventCreationScreen eventScreen = new EventCreationScreen(eventCreationController, screens, cardLayout);
        screens.add("event", eventScreen);

        AssignmentCreationScreen assignmentScreen = new AssignmentCreationScreen(assignmentCreationController, screens, cardLayout);
        screens.add("assignment", assignmentScreen);

        TestCreationScreen testScreen = new TestCreationScreen(testCreationController, screens, cardLayout);
        screens.add("test", testScreen);

        CalendarScreen calendarScreen = new CalendarScreen((StudentUser) user, allTasks, screens, cardLayout);
        screens.add("calendar", calendarScreen);

        ScheduleCTScreen scheduleCTScreen = new ScheduleCTScreen(scheduleCTController, screens, cardLayout);
        screens.add("scheduleCT", scheduleCTScreen);

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

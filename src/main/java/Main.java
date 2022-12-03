import entities.*;
import screens.*;
import screens.calendar_scheduler.*;
import screens.course_progress.*;
import screens.courses_features.*;
import screens.login_registration.*;
import screens.task_management.task_creation_screens.*;
import screens.task_management.todolist_screens.ToDoListPresenter;
import screens.task_management.todolist_screens.ToDoListScreen;
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
import use_cases.login_registration.user_register_usecase.*;
import use_cases.task_management.read_write.TaskReadWrite;
import use_cases.task_management.todolist_use_case.ToDoListInteractor;

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

        //create readwriter - read in TaskMap from file upon program start
        TaskReadWrite taskReadWrite = new TaskReadWrite("src/main/java/data/TaskMap.txt");
        TaskMap.load(taskReadWrite);

        // Get objects from database
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

        ToDoListPresenter toDoListPresenter = new ToDoListPresenter();
        ToDoListInteractor toDoListInteractor = new ToDoListInteractor(toDoListPresenter);

        SchedulerPresenter schedulerPresenter = new SchedulerResponseFormatter();
        ScheduleConflictPresenter scheduleConflictPresenter = new ScheduleConflictResponseFormatter();

        ProgressTrackerOutputBoundary trackerPresenter = new ProgressTrackerPresenter();
        ProgressTrackerInputBoundary trackerInteractor = new ProgressTrackerInteractor(trackerPresenter);
        ProgressTrackerController trackerController = new ProgressTrackerController(trackerInteractor, user, "", TaskMap.getTaskMap(), allUsers, allCourses);

        ScheduleCTViewInterface scheduleCTOutputView = new ScheduleCTView(cardLayout, screens);
        ScheduleCTOutputBoundary scheduleCTPresenter = new ScheduleCTPresenter(scheduleCTOutputView);
        ScheduleCTInputBoundary scheduleCTInteractor = new ScheduleCTInteractor(scheduleCTPresenter);
        ScheduleCTController scheduleCTController = new ScheduleCTController(scheduleCTInteractor, user);

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
        ChooseTaskCreateScreen chooseTask = new ChooseTaskCreateScreen(user, schedulerPresenter, scheduleConflictPresenter,
                screens, cardLayout);
        screens.add("taskCreate", chooseTask);

        ToDoListScreen toDoListScreen = new ToDoListScreen((StudentUser) user, toDoListPresenter, screens, cardLayout);
        screens.add("toDoList", toDoListScreen);

        CalendarScreen calendarScreen = new CalendarScreen((StudentUser) user, TaskMap.getTaskMap(), screens, cardLayout);
        screens.add("calendar", calendarScreen);

        ScheduleCTScreen scheduleCTScreen = new ScheduleCTScreen(scheduleCTController, screens, cardLayout);
        screens.add("scheduleCT", scheduleCTScreen);
        screens.add("scheduleCTView", (Component) scheduleCTOutputView);

        ProgressTrackerScreen progressTrackerScreen = new ProgressTrackerScreen(trackerController);
        screens.add("tracker", progressTrackerScreen);

        CourseCreationScreen courseCreationScreen = new CourseCreationScreen(courseCreationController, screens, cardLayout);
        screens.add("course", courseCreationScreen);

        StudentMainScreen studentMainScreen = new StudentMainScreen((StudentUser)user, screens, cardLayout);
        screens.add("StudentMain", studentMainScreen);

        RegisterScreen registerScreen = new RegisterScreen(userRegisterController, cardLayout, screens);
        screens.add("register", registerScreen);

        LoginScreen loginScreen = new LoginScreen(loginController, cardLayout, screens);
        screens.add("login", loginScreen);

        InstructorMainScreen instructorMainScreen = new InstructorMainScreen(screens, cardLayout);
        screens.add("InstructorMain", instructorMainScreen);

        WelcomeScreen welcomeScreen = new WelcomeScreen(cardLayout, screens);
        screens.add("welcome", welcomeScreen);

        cardLayout.show(screens, "welcome");
        application.pack();
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

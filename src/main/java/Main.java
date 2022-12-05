import entities.*;
import screens.*;
import screens.calendar_scheduler.*;
import screens.course_tracker.*;
import screens.course_features.*;
import screens.login_registration.*;
import screens.task_management.task_creation_screens.*;
import use_cases.course_features.course_creation_use_case.*;
import use_cases.course_tracker.grade_calculator_use_case.GradeCalculatorInputBoundary;
import use_cases.course_tracker.grade_calculator_use_case.GradeCalculatorInteractor;
import use_cases.course_tracker.grade_calculator_use_case.GradeCalculatorOutputBoundary;
import use_cases.course_features.course_enrolment_use_case.*;
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
import screens.task_management.FileTaskMap;

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
        FileTaskMap taskReadWrite = new FileTaskMap("src/main/java/data/TaskMap.txt");
        TaskMap.setTaskMap(taskReadWrite.load());

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

        // delete once everyone's stuff is resolved
        User user;
        if ((((UserRegInteractor) userInteractor).getUser() instanceof StudentUser) |
                (((UserRegInteractor) userInteractor).getUser() instanceof InstructorUser)) {
            user = ((UserRegInteractor) userInteractor).getUser();
        } else {
            user = ((LoginInteractor) loginInteractor).getUser();
        }

        SchedulerPresenter schedulerPresenter = new SchedulerResponseFormatter();
        ScheduleConflictPresenter scheduleConflictPresenter = new ScheduleConflictResponseFormatter();

        CourseEnrolmentDsGateway courseAccess = new FileCourse("src/main/java/data/courses.ser");
        ProgressTrackerScreen progressTrackerScreen = new ProgressTrackerScreen(screens, cardLayout);
        ProgressTrackerOutputBoundary trackerPresenter = new ProgressTrackerPresenter(progressTrackerScreen);
        ProgressTrackerInputBoundary trackerInteractor = new ProgressTrackerInteractor(trackerPresenter, courseAccess);
        ProgressTrackerController trackerController = new ProgressTrackerController(trackerInteractor);

        GradeCalculatorOutputBoundary gradePresenter = new GradeCalculatorPresenter(progressTrackerScreen);
        GradeCalculatorInputBoundary gradeInteractor = new GradeCalculatorInteractor(gradePresenter, courseAccess);
        GradeCalculatorController gradeController = new GradeCalculatorController(gradeInteractor);

        ScheduleCTViewInterface scheduleCTOutputView = new ScheduleCTView(cardLayout, screens);
        ScheduleCTOutputBoundary scheduleCTPresenter = new ScheduleCTPresenter(scheduleCTOutputView);
        ScheduleCTInputBoundary scheduleCTInteractor = new ScheduleCTInteractor(scheduleCTPresenter);
        ScheduleCTController scheduleCTController = new ScheduleCTController(scheduleCTInteractor);

        // Adding in course creation use case
        CourseCreationDsGateway courseCreate = new FileCourse("src/main/java/data/courses.ser");
        CourseCreationOutputBoundary coursePresenter = new CourseCreationPresenter();
        CourseCreationInputBoundary courseInteractor = new CourseCreationInteractor(courseCreate, coursePresenter);
        CourseCreationController courseController = new CourseCreationController(courseInteractor);

        // Adding in course enrolment use case
        CourseEnrolmentDsGateway enrolCourse = new FileCourse("src/main/java/data/courses.ser");
        CourseTasksToStudentTodolistDsGateway tasksToTodolist = new FileUser("src/main/java/data/users.ser");
        CourseEnrolmentOutputBoundary enrolmentPresenter = new CourseEnrolmentPresenter();
        CourseEnrolmentInputBoundary enrolmentInteractor = new CourseEnrolmentInteractor(enrolCourse, tasksToTodolist, enrolmentPresenter);
        CourseEnrolmentController enrolmentController = new CourseEnrolmentController(enrolmentInteractor);

        // Adding in logout use case
        LogoutGateway logoutUser = new FileUser("src/main/java/data/users.ser");
        LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutUser);
        LogoutController logoutController = new LogoutController(logoutInteractor);
        //

        // Build the GUI
        StudentChooseTaskCreateScreen chooseStudentTask = new StudentChooseTaskCreateScreen(schedulerPresenter, scheduleConflictPresenter,
                screens, cardLayout);
        screens.add("studentTaskCreate", chooseStudentTask);

        InstructorChooseTaskCreateScreen chooseInstructortask = new InstructorChooseTaskCreateScreen(screens, cardLayout);
        screens.add("instructorTaskCreate", chooseInstructortask);

        CalendarScreen calendarScreen = new CalendarScreen((StudentUser) user, TaskMap.getTaskMap(), screens, cardLayout);
        screens.add("calendar", calendarScreen);

        ScheduleCTScreen scheduleCTScreen = new ScheduleCTScreen(scheduleCTController, screens, cardLayout);
        screens.add("scheduleCT", scheduleCTScreen);
        screens.add("scheduleCTView", (Component) scheduleCTOutputView);

        progressTrackerScreen.setProgressTrackerController(trackerController);
        progressTrackerScreen.setGradeCalculatorController(gradeController);
        screens.add("tracker", progressTrackerScreen);

        CourseCreationScreen courseCreationScreen = new CourseCreationScreen(courseController, screens, cardLayout);
        screens.add("courseCreate", courseCreationScreen);

        CourseEnrolmentScreen courseEnrolmentScreen = new CourseEnrolmentScreen(enrolmentController, screens, cardLayout);
        screens.add("courseEnrol", courseEnrolmentScreen);

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
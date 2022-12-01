import screens.calendar_scheduler.ScheduleConflictResponseFormatter;
import screens.calendar_scheduler.SchedulerResponseFormatter;
import screens.task_management.event_creation_screens.EventCreationController;
import screens.task_management.event_creation_screens.EventCreationResponseFormatter;
import screens.task_management.event_creation_screens.EventCreationScreen;
import screens.task_management.event_creation_screens.EventCreationController;
import entities.*;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictPresenter;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerPresenter;
import use_cases.task_management.read_write.TaskReadWrite;
import use_cases.task_management.task_creation_use_case.TaskCreationInputBoundary;
import use_cases.task_management.task_creation_use_case.TaskCreationInteractor;
import use_cases.task_management.task_creation_use_case.TaskCreationPresenter;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class TempMain {
    public static void main(String[] args) {
        // Build the main program window
        JFrame application = new JFrame("Login Example");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        SchedulerPresenter schedulerPresenter = new SchedulerResponseFormatter();
        ScheduleConflictPresenter scheduleConflictPresenter = new ScheduleConflictResponseFormatter();

        TaskCreationPresenter presenter = new EventCreationResponseFormatter();

        // create test student - use current student when everything else is in main
        StudentUser fakeStudent = new StudentUser("name", "password");

        //create readwriter - read in from file upon program start
        TaskReadWrite taskReadWrite = new TaskReadWrite("src/main/java/data/TaskMap.txt");
        TaskMap.load(taskReadWrite);
        if (TaskMap.getTaskMap() == null) {
            //if TaskMap.txt is empty, initialize taskMap static variable as empty HashMap
            TaskMap.setTaskMap(new HashMap<String, Task>());
        }

        TaskCreationInputBoundary interactor = new TaskCreationInteractor(
                presenter, fakeStudent, "none",
                schedulerPresenter, scheduleConflictPresenter);
        EventCreationController eventCreationController = new EventCreationController(interactor);

        // Build the GUI, plugging in the parts
        EventCreationScreen eventCreationScreen = new EventCreationScreen(eventCreationController, screens, cardLayout);
        screens.add(eventCreationScreen, "welcome");
        cardLayout.show(screens, "create event");
        application.pack();
        application.setVisible(true);
    }
}

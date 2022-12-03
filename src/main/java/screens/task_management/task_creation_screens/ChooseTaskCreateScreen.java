package screens.task_management.task_creation_screens;

import entities.User;
import screens.task_management.task_creation_screens.assignment_creation_screens.AssignmentCreationController;
import screens.task_management.task_creation_screens.assignment_creation_screens.AssignmentCreationScreen;
import screens.task_management.task_creation_screens.event_creation_screens.EventCreationController;
import screens.task_management.task_creation_screens.event_creation_screens.EventCreationScreen;
import screens.task_management.task_creation_screens.test_creation_screens.TestCreationController;
import screens.task_management.task_creation_screens.test_creation_screens.TestCreationScreen;
import use_cases.calendar_scheduler.schedule_conflict_use_case.*;
import use_cases.calendar_scheduler.scheduler_use_case.*;
import use_cases.task_management.task_creation_use_case.TaskCreationInputBoundary;
import use_cases.task_management.task_creation_use_case.TaskCreationInteractor;
import use_cases.task_management.task_creation_use_case.TaskCreationOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseTaskCreateScreen extends JPanel implements ActionListener {
    // selectable buttons on screen
    JButton event = new JButton("New event");
    JButton assignment = new JButton("New assignment");
    JButton test = new JButton("New test");
    JButton cancel = new JButton("Cancel");

    // for making task creation screens:
    User user;
    ScheduleConflictOutputBoundary scheduleConflictPresenter;

    // for connecting to other screens
    CardLayout cardLayout;
    JPanel screens;

    /**
     * the window for choosing which type of Task to create, after selecting "New task"
     */
    public ChooseTaskCreateScreen(User user, ScheduleConflictOutputBoundary scheduleConflictPresenter,
                                  JPanel screens, CardLayout cardLayout) {
        this.user = user;
        this.scheduleConflictPresenter = scheduleConflictPresenter;
        this.cardLayout = cardLayout;
        this.screens = screens;

        // Create label for title of screen
        JLabel title = new JLabel("Select a task type");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add action listeners to buttons
        event.addActionListener(this);
        assignment.addActionListener(this);
        test.addActionListener(this);
        cancel.addActionListener(this);

        // Create panel for buttons
        JPanel buttons = new JPanel();
        buttons.add(event);
        buttons.add(assignment);
        buttons.add(test);
        buttons.add(cancel);

        // Add all components to the panel
        this.add(title);
        this.add(buttons);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Redirect user to Task creation screen corresponding to button click
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cancel")) { // go back to main
            cardLayout.show(screens, "main");
        }
        // create use case components for task creation
        TaskCreationOutputBoundary taskCreationOutputBoundary = new TaskCreationResponseFormatter();
        TaskCreationInputBoundary taskInteractor = new TaskCreationInteractor(
                taskCreationOutputBoundary, user, "none", scheduleConflictPresenter);
        EventCreationController eventCreationController = new EventCreationController(taskInteractor);
        AssignmentCreationController assignmentCreationController = new AssignmentCreationController(taskInteractor);
        TestCreationController testCreationController = new TestCreationController(taskInteractor);

        if (e.getActionCommand().equals("New event")) { // create and go to event screen
            EventCreationScreen eventCreationScreen = new EventCreationScreen(eventCreationController, screens, cardLayout);

            screens.add("event", eventCreationScreen);
            cardLayout.show(screens, "event");
        } else if (e.getActionCommand().equals("New assignment")) { // create and go to assignment screen
            AssignmentCreationScreen assignmentCreationScreen = new AssignmentCreationScreen(assignmentCreationController, screens, cardLayout);

            screens.add("assignment", assignmentCreationScreen);
            cardLayout.show(screens, "assignment");
        } else { // create and go to test screen
            TestCreationScreen testCreationScreen = new TestCreationScreen(testCreationController, screens, cardLayout);

            screens.add("test", testCreationScreen);
            cardLayout.show(screens, "test");
        }
    }
}

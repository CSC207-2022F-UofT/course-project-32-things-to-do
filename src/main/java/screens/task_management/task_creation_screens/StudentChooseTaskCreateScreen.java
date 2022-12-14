package screens.task_management.task_creation_screens;

import screens.task_management.FileTaskMap;
import screens.task_management.task_creation_screens.assignment_creation_screens.*;
import screens.task_management.task_creation_screens.event_creation_screens.*;
import screens.task_management.task_creation_screens.test_creation_screens.*;
import use_cases.calendar_scheduler.schedule_conflict_use_case.*;
import use_cases.task_management.read_write.TaskMapGateway;
import use_cases.task_management.task_creation_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentChooseTaskCreateScreen extends JPanel implements ActionListener {
    // selectable buttons on screen
    JButton event = new JButton("New event");
    JButton assignment = new JButton("New assignment");
    JButton test = new JButton("New test");
    JButton cancel = new JButton("Cancel");

    // for making task creation screens:
    ScheduleConflictOutputBoundary scheduleConflictPresenter;

    // for connecting to other screens
    CardLayout cardLayout;
    JPanel screens;

    /**
     * The window for deciding which Task to create, after clicking the "New task" button
     * @param scheduleConflictPresenter - output boundary for the scheduling conflict use case
     * @param screens - rest of screens in the program
     * @param cardLayout - for switching between screens
     */
    public StudentChooseTaskCreateScreen(ScheduleConflictOutputBoundary scheduleConflictPresenter,
                                         JPanel screens, CardLayout cardLayout) {
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
        // create use case components for task creation
        TaskCreationOutputBoundary taskCreationOutputBoundary = new TaskCreationResponseFormatter();
        TaskMapGateway taskMapGateway = new FileTaskMap("src/main/java/data/TaskMap.txt");
        TaskCreationInputBoundary taskInteractor = new TaskCreationInteractor(
                taskMapGateway, taskCreationOutputBoundary, "none", scheduleConflictPresenter);
        EventCreationController eventCreationController = new EventCreationController(taskInteractor);
        AssignmentCreationController assignmentCreationController = new AssignmentCreationController(taskInteractor);
        TestCreationController testCreationController = new TestCreationController(taskInteractor);

        if (e.getActionCommand().equals("New event")) { // create and go to event screen
            EventCreationScreen eventCreationScreen = new EventCreationScreen(eventCreationController, screens, cardLayout);

            screens.add("event", eventCreationScreen);
            cardLayout.show(screens, "event");
        } else if (e.getActionCommand().equals("New assignment")) { // create and go to assignment screen
            StudentAssignmentCreationScreen assignmentCreationScreen = new StudentAssignmentCreationScreen(assignmentCreationController, screens, cardLayout);

            screens.add("studentAssignment", assignmentCreationScreen);
            cardLayout.show(screens, "studentAssignment");
        } else if (e.getActionCommand().equals("New test")){ // create and go to test screen
            StudentTestCreationScreen testCreationScreen = new StudentTestCreationScreen(testCreationController, screens, cardLayout);

            screens.add("studentTest", testCreationScreen);
            cardLayout.show(screens, "studentTest");
        } else { // cancel button pressed
            cardLayout.show(screens, "StudentMain");
        }
    }
}

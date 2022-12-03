package screens.task_management.task_creation_screens;

import screens.task_management.FileTaskMap;
import screens.task_management.task_creation_screens.assignment_creation_screens.AssignmentCreationController;
import screens.task_management.task_creation_screens.assignment_creation_screens.InstructorAssignmentCreationScreen;
import screens.task_management.task_creation_screens.test_creation_screens.InstructorTestCreationScreen;
import screens.task_management.task_creation_screens.test_creation_screens.TestCreationController;
import use_cases.task_management.read_write.TaskMapGateway;
import use_cases.task_management.task_creation_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructorChooseTaskCreateScreen extends JPanel implements ActionListener {
    // selectable buttons on screen
    JButton assignment = new JButton("New assignment");
    JButton test = new JButton("New test");
    JButton cancel = new JButton("Cancel");

    // for connecting to other screens
    CardLayout cardLayout;
    JPanel screens;

    /**
     * The window for deciding which Task to create, after clicking the "New task" button
     * @param screens - rest of screens in the program
     * @param cardLayout - for switching between screens
     */
    public InstructorChooseTaskCreateScreen(JPanel screens, CardLayout cardLayout) {
        this.cardLayout = cardLayout;
        this.screens = screens;

        // Create label for title of screen
        JLabel title = new JLabel("Select a task type");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add action listeners to buttons
        assignment.addActionListener(this);
        test.addActionListener(this);
        cancel.addActionListener(this);

        // Create panel for buttons
        JPanel buttons = new JPanel();
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
                taskMapGateway, taskCreationOutputBoundary, "none");
        AssignmentCreationController assignmentCreationController = new AssignmentCreationController(taskInteractor);
        TestCreationController testCreationController = new TestCreationController(taskInteractor);

        if (e.getActionCommand().equals("New assignment")) { // create and go to assignment screen
            InstructorAssignmentCreationScreen instructorAssignment = new InstructorAssignmentCreationScreen(assignmentCreationController, screens, cardLayout);

            screens.add("instructorAssignment", instructorAssignment);
            cardLayout.show(screens, "instructorAssignment");
        } else if (e.getActionCommand().equals("New test")) { // create and go to test screen
            InstructorTestCreationScreen instructorTest = new InstructorTestCreationScreen(testCreationController, screens, cardLayout);

            screens.add("instructorTest", instructorTest);
            cardLayout.show(screens, "instructorTest");
        } else if (e.getActionCommand().equals("Cancel")) { // go back to main
            cardLayout.show(screens, "InstructorMain");
        }
    }
}

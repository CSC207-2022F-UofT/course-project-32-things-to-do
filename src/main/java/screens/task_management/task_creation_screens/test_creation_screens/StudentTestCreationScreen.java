package screens.task_management.task_creation_screens.test_creation_screens;

import screens.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import static javax.swing.JOptionPane.showMessageDialog;

public class StudentTestCreationScreen extends JPanel implements ActionListener {
    // text fields
    JTextField title = new JTextField(15);
    JTextField priority = new JTextField(15);
    JTextField date = new JTextField(15);
    JTextField startTime = new JTextField(15);
    JTextField endTime = new JTextField(15);
    JTextField weightage = new JTextField(15);

    // controller
    TestCreationController testController;

    // to access rest of screens in program
    JPanel screens;
    CardLayout screenLayout;

    /**
     * A screen for Test creation
     * @param testController - controller which calls the creation use case
     * @param screens - rest of screens in the program
     * @param screenLayout - for switching between screens
     */
    public StudentTestCreationScreen(TestCreationController testController, JPanel screens, CardLayout screenLayout) {
        this.testController = testController;
        this.screens = screens;
        this.screenLayout = screenLayout;

        // set screen title
        JLabel screenTitle = new JLabel("Test Creation Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // create labels for all text fields
        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Enter test title"), title);
        LabelTextPanel prioInfo = new LabelTextPanel(
                new JLabel("Enter test priority (integer)"), priority);
        LabelTextPanel dateInfo = new LabelTextPanel(
                new JLabel("Enter test date (yyyy-MM-dd)"), date);
        LabelTextPanel startTimeInfo = new LabelTextPanel(
                new JLabel("Enter test start time (hh:mm, 24 hour)"), startTime);
        LabelTextPanel endTimeInfo = new LabelTextPanel(
                new JLabel("Enter test end time (hh:mm, 24 hour)"), endTime);
        LabelTextPanel weightInfo = new LabelTextPanel(
                new JLabel("Enter test weightage (double, don't include %)"), weightage);

        // finish and cancel buttons
        JButton finish = new JButton("Finish");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(finish);
        buttons.add(cancel);

        // add action listeners for buttons
        finish.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // add all components to panel
        this.add(screenTitle);
        this.add(titleInfo);
        this.add(prioInfo);
        this.add(dateInfo);
        this.add(startTimeInfo);
        this.add(endTimeInfo);
        this.add(weightInfo);
        this.add(buttons);
    }
    /**
     * React to button presses
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // if "Finish" button pressed
        if (evt.getActionCommand().equals("Finish")) {
            try {
                // set priority to the value in the box or 0 if blank
                int valPriority = priority.getText().equals("") ? 0 : Integer.parseInt(priority.getText());
                if (valPriority < 0) valPriority = 0;
                // get the start and end date+times and parse them
                LocalDateTime startDate = LocalDateTime.parse(date.getText() + "T" + startTime.getText());
                LocalDateTime endDate = LocalDateTime.parse(date.getText() + "T" + endTime.getText());
                // set weightage value to value in the box or 0 if blank
                double valWeightage = weightage.getText().equals("") ? 0.0 : Double.parseDouble(weightage.getText());

                // create the Test
                testController.create(title.getText(), valPriority, startDate, endDate, valWeightage);

                // notify user of success and return to main screen
                showMessageDialog(this, "Test Created Successfully");
                screenLayout.show(screens, "StudentMain");
            } catch (Exception e) { // if there are any input issues (eg parsing)
                showMessageDialog(this, e.getMessage());
            }
        }
        // if "Cancel" button pressed
        else if (evt.getActionCommand().equals("Cancel")) {
            // return to main screen
            screenLayout.show(screens, "StudentMain");
        }
    }
}

package screens.task_management.task_creation_screens.test_creation_screens;

import screens.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * screen for when instructors create Tests (same as student except missing priority value)
 */
public class InstructorTestCreationScreen extends JPanel implements ActionListener {
    // text fields
    JTextField title = new JTextField(15);
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
     * A screen for making (instructor) Tests
     * @param testController - controller for calling Task creation use case
     * @param screens - rest of screens in the program
     * @param screenLayout - for switching between screens
     */
    public InstructorTestCreationScreen(TestCreationController testController, JPanel screens, CardLayout screenLayout) {
        this.testController = testController;
        this.screens = screens;
        this.screenLayout = screenLayout;

        // set the title of the screen
        JLabel screenTitle = new JLabel("Test Creation Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // create labels for all text fields
        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Enter test title"), title);
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
                // get the start and end date+times and parse them
                LocalDateTime startDate = LocalDateTime.parse(date.getText() + "T" + startTime.getText());
                LocalDateTime endDate = LocalDateTime.parse(date.getText() + "T" + endTime.getText());
                // set weightage value to value in the box or 0 if blank
                double valWeightage = weightage.getText().equals("") ? 0.0 : Double.parseDouble(weightage.getText());

                // create the Test
                testController.create(title.getText(), 0, startDate, endDate, valWeightage);

                // notify user of success and return to main screen
                showMessageDialog(this, "Test Created Successfully");
                screenLayout.show(screens, "InstructorMain");
            } catch (Exception e) { // if anything goes wrong in the input (e.g. parsing error)
                showMessageDialog(this, e.getMessage());
            }
        }
        // if "Cancel" button pressed
        else if (evt.getActionCommand().equals("Cancel")) {
            // return to main screen
            screenLayout.show(screens, "InstructorMain");
        }
    }
}

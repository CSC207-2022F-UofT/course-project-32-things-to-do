package screens.task_management.task_creation_screens.assignment_creation_screens;

import screens.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * screen for when instructors create Assignments (same as student except missing priority value)
 */
public class InstructorAssignmentCreationScreen extends JPanel implements ActionListener {
    // text fields
    JTextField title = new JTextField(15);
    JTextField dueDay = new JTextField(15);
    JTextField dueTime = new JTextField(15);
    JTextField weightage = new JTextField(15);

    // controller
    AssignmentCreationController assignmentController;

    // to access other screens in program
    JPanel screens;
    CardLayout screenlayout;

    public InstructorAssignmentCreationScreen(AssignmentCreationController assignmentController, JPanel screens, CardLayout screenLayout) {
        this.assignmentController = assignmentController;
        this.screens = screens;
        this.screenlayout = screenLayout;

        JLabel screenTitle = new JLabel("Assignment Creation Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // create labels for all text fields
        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Enter Assignment title:"), title);
        LabelTextPanel dueDayInfo = new LabelTextPanel(
                new JLabel("Enter Assignment due date (yyyy-MM-dd):"), dueDay);
        LabelTextPanel dueTimeInfo = new LabelTextPanel(
                new JLabel("Enter Assignment due time (hh:mm):"), dueTime);
        LabelTextPanel weightInfo = new LabelTextPanel(
                new JLabel("Enter Assignment weightage (double, don't include %):"), weightage);

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
        this.add(dueDayInfo);
        this.add(dueTimeInfo);
        this.add(weightInfo);
        this.add(buttons);
    }

    /**
     * React to button presses
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Finish")) {
            try {
                // get the due date+time and parse
                LocalDateTime dueDate = LocalDateTime.parse(dueDay.getText() + "T" + dueTime.getText() + ":00");
                // set weightage to value in the box or 0.0 if blank
                double valWeightage = weightage.getText().equals("") ? 0.0 : Double.parseDouble(weightage.getText());

                assignmentController.create(title.getText(), 0, dueDate, valWeightage);

                showMessageDialog(this, "Assignment Created Successfully");
            } catch (Exception e) {
                showMessageDialog(this, e.getMessage());
            }
        }
        // if "Cancel" button pressed
        else if (evt.getActionCommand().equals("Cancel")) {
            screenlayout.show(screens, "main");
        }
    }
}

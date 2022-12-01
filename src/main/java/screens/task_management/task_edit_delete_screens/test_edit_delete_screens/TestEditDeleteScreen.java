package screens.task_management.task_edit_delete_screens.test_edit_delete_screens;

import entities.StudentUser;
import screens.LabelTextPanel;
import screens.task_management.task_creation_screens.test_creation_screens.TestCreationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestEditDeleteScreen extends JPanel implements ActionListener {
    // text fields
    JTextField title;
    JTextField priority;
    JTextField date;
    JTextField startTime;
    JTextField endTime;
    JTextField weightage;

    // todo get rid of this somehow (in all edit screens)
    StudentUser student;

    // controller
    TestCreationController testController;

    // to access rest of screens in program
    JPanel screens;
    CardLayout screenLayout;

    public TestEditDeleteScreen(StudentUser student,
                                TestCreationController testController, JPanel screens, CardLayout screenLayout) {
        this.student = student;
        this.testController = testController;
        this.screens = screens;
        this.screenLayout = screenLayout;

        JLabel screenTitle = new JLabel("Test Edit/Delete Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // create labels for all text fields
        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Enter new test title"), title);
        LabelTextPanel prioInfo = new LabelTextPanel(
                new JLabel("Enter new test priority (integer)"), priority);
        LabelTextPanel dateInfo = new LabelTextPanel(
                new JLabel("Enter new test start date (yyyy-MM-dd)"), date);
        LabelTextPanel startTimeInfo = new LabelTextPanel(
                new JLabel("Enter new test start time (hh:mm)"), startTime);
        LabelTextPanel endTimeInfo = new LabelTextPanel(
                new JLabel("Enter new test end time (hh:mm)"), endTime);
        LabelTextPanel weightInfo = new LabelTextPanel(
                new JLabel("Enter new test weightage (double, don't include %)"), weightage);

        // finish, delete and cancel buttons
        JButton finish = new JButton("Finish");
        JButton delete = new JButton("Delete");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(finish);
        buttons.add(delete);
        buttons.add(cancel);

        // add action listeners for buttons
        finish.addActionListener(this);
        delete.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // todo implement this
    }
}

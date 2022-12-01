package screens.task_management.task_edit_delete_screens.test_edit_delete_screens;

import entities.StudentUser;
import screens.LabelTextPanel;
import screens.task_management.task_edit_delete_screens.TaskDeletionController;
import use_cases.task_management.task_edit_use_case.TestDisplayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.showMessageDialog;

public class TestEditDeleteScreen extends JPanel implements ActionListener {
    // user input
    JCheckBox complete = new JCheckBox("Mark task complete?");
    JTextField title;
    JTextField priority;
    JTextField date;
    JTextField startTime;
    JTextField endTime;
    JTextField weightage;
    JTextField timeNeeded;
    JTextField timeSpent;

    // todo get rid of this somehow (in all edit screens)
    StudentUser student;

    // controllers
    TestEditController testEditController;
    TaskDeletionController taskDeletionController;

    // to access rest of screens in program
    JPanel screens;
    CardLayout screenLayout;

    TestDisplayer testInfo;

    public TestEditDeleteScreen(StudentUser student,
                                TestEditController testEditController, TaskDeletionController taskDeletionController,
                                JPanel screens, CardLayout screenLayout, TestDisplayer testInfo) {
        this.student = student;
        this.testEditController = testEditController;
        this.taskDeletionController = taskDeletionController;
        this.screens = screens;
        this.screenLayout = screenLayout;
        this.testInfo = testInfo;

        JLabel screenTitle = new JLabel("Test Edit/Delete Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // fill all text fields and whatnot
        title = new JTextField(testInfo.getTitle());
        priority = new JTextField(testInfo.getPriority());
        date = new JTextField(testInfo.getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE));
        startTime = new JTextField(testInfo.getStartTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        endTime = new JTextField(testInfo.getEndTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        weightage = new JTextField("" + testInfo.getWeightage());
        timeNeeded = new JTextField("" + testInfo.getTimeNeeded());
        timeSpent = new JTextField("" + testInfo.getTimeSpent());

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
        LabelTextPanel timeNeededInfo = new LabelTextPanel(
                new JLabel("How long do you need to complete this? (hours)"), timeNeeded);
        LabelTextPanel timeSpentInfo = new LabelTextPanel(
                new JLabel("How long have you spent on this already? (hours)"), timeSpent);

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
        this.add(timeNeededInfo);
        this.add(timeSpentInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("Finish")) { // Test being edited
                // check if marked complete
                boolean valComplete = complete.isSelected();
                // change to original value if field left blank
                String valTitle = title.getText().equals("") ? testInfo.getTitle() : title.getText();
                int valPriority = priority.getText().equals("") ? testInfo.getPriority() : Integer.parseInt(priority.getText());
                LocalDateTime valStartTime = date.getText().equals("") || startTime.getText().equals("") ?
                        testInfo.getStartTime() : LocalDateTime.parse(date.getText() + "T" + startTime.getText() + ":00");
                LocalDateTime valEndTime = date.getText().equals("") || endTime.getText().equals("") ?
                        testInfo.getEndTime() : LocalDateTime.parse(date.getText() + "T" + endTime.getText() + ":00");
                double valWeightage = weightage.getText().equals("") ? testInfo.getWeightage() : Double.parseDouble(weightage.getText());
                double valTimeNeeded = timeNeeded.getText().equals("") ? testInfo.getTimeNeeded() : Double.parseDouble(timeNeeded.getText());
                double valTimeSpent = timeSpent.getText().equals("") ? testInfo.getTimeSpent() : Double.parseDouble(timeSpent.getText());

                // edit the Test
                testEditController.edit(valComplete, testInfo.getId(), valTitle, valPriority, valStartTime, valEndTime, valWeightage,
                        valTimeNeeded, valTimeSpent);

            } else if (e.getActionCommand().equals("Delete")) { // Test being deleted
                taskDeletionController.delete(student, testInfo.getId());
            } else { // Test is racist
                screenLayout.show(screens, "main");
            }
        } catch (Exception ex) {
            showMessageDialog(this, ex.getMessage());
        }
    }
}

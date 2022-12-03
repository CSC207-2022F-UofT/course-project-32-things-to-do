package screens.task_management.task_edit_delete_screens.test_edit_delete_screens;

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

    // controllers
    TestEditController testEditController;
    TaskDeletionController taskDeletionController;

    // to access rest of screens in program
    JPanel screens;
    CardLayout screenLayout;

    // for getting the info about a Test
    TestDisplayer testInfo;

    /**
     * A screen for editing/deleting a Test
     * @param testEditController - controller for editing
     * @param taskDeletionController - controller for deleting
     * @param screens - rest of screens in the program
     * @param screenLayout - for switching between screens
     * @param testInfo - for accessing info about a test
     */
    public TestEditDeleteScreen(
                                TestEditController testEditController, TaskDeletionController taskDeletionController,
                                JPanel screens, CardLayout screenLayout, TestDisplayer testInfo) {
        this.testEditController = testEditController;
        this.taskDeletionController = taskDeletionController;
        this.screens = screens;
        this.screenLayout = screenLayout;
        this.testInfo = testInfo;

        // set screen title
        JLabel screenTitle = new JLabel("Test Edit/Delete Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // fill all text fields and whatnot
        title = new JTextField(testInfo.getTitle(), 15);
        title.setEnabled(false);
        priority = new JTextField("" + testInfo.getPriority(), 15);
        date = new JTextField(testInfo.getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE), 15);
        startTime = new JTextField(testInfo.getStartTime().format(DateTimeFormatter.ISO_LOCAL_TIME), 15);
        endTime = new JTextField(testInfo.getEndTime().format(DateTimeFormatter.ISO_LOCAL_TIME), 15);
        weightage = new JTextField("" + testInfo.getWeightage(), 15);
        timeNeeded = new JTextField("" + testInfo.getTimeNeeded(), 15);
        timeSpent = new JTextField("" + testInfo.getTimeSpent(), 15);

        // create labels for all text fields
        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Test title:"), title);
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
        this.add(complete);
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

    /**
     * React to button clicks
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("Finish")) { // Test being edited
                // check if marked complete
                boolean valComplete = complete.isSelected();
                // change to original value if field left blank
                int valPriority = priority.getText().equals("") ? testInfo.getPriority() : Integer.parseInt(priority.getText());
                LocalDateTime valStartTime = date.getText().equals("") || startTime.getText().equals("") ?
                        testInfo.getStartTime() : LocalDateTime.parse(date.getText() + "T" + startTime.getText());
                LocalDateTime valEndTime = date.getText().equals("") || endTime.getText().equals("") ?
                        testInfo.getEndTime() : LocalDateTime.parse(date.getText() + "T" + endTime.getText());
                double valWeightage = weightage.getText().equals("") ? testInfo.getWeightage() : Double.parseDouble(weightage.getText());
                double valTimeNeeded = timeNeeded.getText().equals("") ? testInfo.getTimeNeeded() : Double.parseDouble(timeNeeded.getText());
                double valTimeSpent = timeSpent.getText().equals("") ? testInfo.getTimeSpent() : Double.parseDouble(timeSpent.getText());

                // edit the Test
                testEditController.edit(valComplete, testInfo.getId(), valPriority, valStartTime, valEndTime, valWeightage,
                        valTimeNeeded, valTimeSpent);

                // update user and return to main list page
                showMessageDialog(this, "Test edited successfully."); // todo customize this message
                screenLayout.show(screens, "StudentMain");
            } else if (e.getActionCommand().equals("Delete")) { // Test being deleted
                // delete Test
                taskDeletionController.delete(testInfo.getId());

                // update user and return to to-do list page
                showMessageDialog(this, "Test deleted successfully.");
                screenLayout.show(screens, "toDoList");
            } else { // Test is racist
                screenLayout.show(screens, "StudentMain");
            }
        } catch (Exception ex) {
            showMessageDialog(this, ex.getMessage());
        }
    }
}

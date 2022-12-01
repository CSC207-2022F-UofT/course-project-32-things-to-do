package screens.task_management.task_edit_delete_screens.assignment_edit_delete_screens;

import entities.StudentUser;
import screens.LabelTextPanel;
import screens.task_management.task_edit_delete_screens.TaskDeletionController;
import use_cases.task_management.task_edit_use_case.AssignmentDisplayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.showMessageDialog;

public class AssignmentEditDeleteScreen extends JPanel implements ActionListener {
    // user input
    JCheckBox complete = new JCheckBox("Mark task complete?");
    JTextField title;
    JTextField priority;
    JTextField dueDay;
    JTextField dueTime;
    JTextField weightage;
    JTextField timeNeeded;
    JTextField timeSpent;

    // todo: this needs to leave
    StudentUser student;

    // controllers
    AssignmentEditController assignmentEditController;
    TaskDeletionController taskDeletionController;

    // to access rest of screens in program
    JPanel screens;
    CardLayout screenLayout;

    // to access info of assignment being edited/deleted
    AssignmentDisplayer assignmentInfo;

    // todo: get rid of student from params
    public AssignmentEditDeleteScreen(
            StudentUser student,
            AssignmentEditController assignmentEditController, TaskDeletionController taskDeletionController,
            JPanel screens, CardLayout screenLayout, AssignmentDisplayer assignmentInfo) {
        this.student = student;
        this.assignmentEditController = assignmentEditController;
        this.taskDeletionController = taskDeletionController;
        this.screens = screens;
        this.screenLayout = screenLayout;
        this.assignmentInfo = assignmentInfo;

        JLabel screenTitle = new JLabel("Assignment Edit/Delete Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // fill all text fields
        title = new JTextField(assignmentInfo.getTitle());
        priority = new JTextField("" + assignmentInfo.getPriority());
        dueDay = new JTextField(assignmentInfo.getDueDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        dueTime = new JTextField(assignmentInfo.getDueDate().format(DateTimeFormatter.ISO_LOCAL_TIME));
        timeNeeded = new JTextField("" + assignmentInfo.getTimeNeeded());
        timeSpent = new JTextField("" + assignmentInfo.getTimeSpent());

        // create labels for all text fields
        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Enter new Assignment title:"), title);
        LabelTextPanel prioInfo = new LabelTextPanel(
                new JLabel("Enter new Assignment priority (integer):"), priority);
        LabelTextPanel dueDayInfo = new LabelTextPanel(
                new JLabel("Enter new Assignment due date (yyyy-MM-dd):"), dueDay);
        LabelTextPanel dueTimeInfo = new LabelTextPanel(
                new JLabel("Enter new Assignment due time (hh:mm):"), dueTime);
        LabelTextPanel weightInfo = new LabelTextPanel(
                new JLabel("Enter new Assignment weightage (double, don't include %):"), weightage);
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
        this.add(dueDayInfo);
        this.add(dueTimeInfo);
        this.add(weightInfo);
        this.add(timeNeededInfo);
        this.add(timeSpentInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Assignment being edited
            if (e.getActionCommand().equals("Finish")) {
                // check if marked complete
                boolean valComplete = complete.isSelected();
                // change to original value if field left blank
                String valTitle = title.getText().equals("") ? assignmentInfo.getTitle() : title.getText();
                int valPriority = priority.getText().equals("") ? assignmentInfo.getPriority() : Integer.parseInt(priority.getText());
                LocalDateTime valDueDate = dueDay.getText().equals("") || dueTime.getText().equals("") ?
                        assignmentInfo.getDueDate() : LocalDateTime.parse(dueDay.getText() + "T" + dueTime.getText() + ":00");
                double valWeightage = weightage.getText().equals("") ? assignmentInfo.getWeightage() : Double.parseDouble(weightage.getText());
                double valTimeNeeded = timeNeeded.getText().equals("") ? assignmentInfo.getTimeNeeded() : Double.parseDouble(timeNeeded.getText());
                double valTimeSpent = timeSpent.getText().equals("") ? assignmentInfo.getTimeSpent() : Double.parseDouble(timeSpent.getText());
                // edit the Assignment
                assignmentEditController.edit(valComplete, assignmentInfo.getId(), valTitle, valPriority, valDueDate, valWeightage,
                        valTimeNeeded, valTimeSpent);
            }
            // Assignment being deleted
            else if (e.getActionCommand().equals("Delete")) {
                taskDeletionController.delete(student, assignmentInfo.getId());
            }
            // Cancel button pressed
            else {
                screenLayout.show(screens, "main");
            }
        } catch (Exception ex) {
            showMessageDialog(this, ex.getMessage());
        }
    }
}

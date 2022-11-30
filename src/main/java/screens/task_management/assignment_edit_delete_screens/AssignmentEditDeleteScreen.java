package screens.task_management.assignment_edit_delete_screens;

import entities.StudentUser;
import screens.LabelTextPanel;
import screens.task_management.TaskDeletionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.showMessageDialog;

/*
screen -> controller -> use case -> entity
screen -> task displayer (interface) -> use case -> entity

click edit/delete button
-> program gets all info
-> program displays screen with prefilled values
-> user enters new values
 */

public class AssignmentEditDeleteScreen extends JPanel implements ActionListener {
    // text fields
    JTextField title;
    JTextField priority;
    JTextField dueDay;
    JTextField dueTime;
    JTextField weightage;

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

        // create labels for all text fields
        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Enter Assignment title:"), title);
        LabelTextPanel prioInfo = new LabelTextPanel(
                new JLabel("Enter Assignment priority (integer):"), priority);
        LabelTextPanel dueDayInfo = new LabelTextPanel(
                new JLabel("Enter Assignment due date (yyyy-MM-dd):"), dueDay);
        LabelTextPanel dueTimeInfo = new LabelTextPanel(
                new JLabel("Enter Assignment due time (hh:mm):"), dueTime);
        LabelTextPanel weightInfo = new LabelTextPanel(
                new JLabel("Enter Assignment weightage (double, don't include %):"), weightage);

        // finish and cancel buttons
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
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // assignment being edited
            if (e.getActionCommand().equals("Finish")) {
                // change to original value if field left blank
                String valTitle = title.getText().equals("") ? assignmentInfo.getTitle() : title.getText();
                int valPriority = priority.getText().equals("") ? assignmentInfo.getPriority() : Integer.parseInt(priority.getText());
                LocalDateTime valDueDate = dueDay.getText().equals("") || dueTime.getText().equals("") ?
                        assignmentInfo.getDueDate() : LocalDateTime.parse(dueDay.getText() + "T" + dueTime.getText() + ":00");
                double valWeightage = weightage.getText().equals("") ? assignmentInfo.getWeightage() : Double.parseDouble(weightage.getText());

                assignmentEditController.edit(assignmentInfo.getId(), valTitle, valPriority, valDueDate, valWeightage);
            }
            // assignment being deleted
            else if (e.getActionCommand().equals("Delete")) {
                taskDeletionController.delete(student, assignmentInfo.getId());
            }
            // cancel button pressed
            else {
                screenLayout.show(screens, "main");
            }
        } catch (Exception ex) {
            showMessageDialog(this, ex.getMessage());
        }
    }
}

package screens.task_management.task_edit_delete_screens.event_edit_delete_screens;

import entities.StudentUser;
import screens.LabelTextPanel;
import screens.task_management.task_edit_delete_screens.TaskDeletionController;
import use_cases.task_management.task_edit_use_case.EventDisplayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.showMessageDialog;

public class EventEditDeleteScreen extends JPanel implements ActionListener {
    // user input
    JCheckBox complete = new JCheckBox("Mark task complete?");
    JTextField title;
    JTextField priority;
    JTextField date;
    JTextField startTime;
    JTextField endTime;
    JCheckBox recurring;
    JTextField frequency;

    // todo: this needs to leave
    StudentUser student;

    // controllers
    EventEditController eventEditController;
    TaskDeletionController taskDeletionController;

    // to access rest of screens in program
    JPanel screens;
    CardLayout screenLayout;

    // to access info of assignment being edited/deleted
    EventDisplayer eventInfo;

    public EventEditDeleteScreen(StudentUser student, EventEditController eventEditController,
                                 TaskDeletionController taskDeletionController, JPanel screens,
                                 CardLayout screenLayout) {
        this.student = student;
        this.eventEditController = eventEditController;
        this.taskDeletionController = taskDeletionController;
        this.screens = screens;
        this.screenLayout = screenLayout;

        JLabel screenTitle = new JLabel("Event Edit/Delete Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // fill all text fields and whatnot
        title = new JTextField(eventInfo.getTitle());
        priority = new JTextField(eventInfo.getPriority());
        date = new JTextField(eventInfo.getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE));
        startTime = new JTextField(eventInfo.getStartTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        endTime = new JTextField(eventInfo.getEndTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        recurring = new JCheckBox("Is the event recurring?", eventInfo.getRecurring());
        frequency = new JTextField(eventInfo.getFrequency());

        // create labels for all text fields
        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Enter new event title"), title);
        LabelTextPanel prioInfo = new LabelTextPanel(
                new JLabel("Enter new event priority (integer)"), priority);
        LabelTextPanel dateInfo = new LabelTextPanel(
                new JLabel("Enter new event start date (yyyy-MM-dd)"), date);
        LabelTextPanel startTimeInfo = new LabelTextPanel(
                new JLabel("Enter new event start time (hh:mm)"), startTime);
        LabelTextPanel endTimeInfo = new LabelTextPanel(
                new JLabel("Enter new event end time (hh:mm)"), endTime);
        LabelTextPanel frequencyInfo = new LabelTextPanel(
                new JLabel("Enter new frequency of event"), frequency);
        frequency.setEnabled(recurring.isSelected());

        // finish, delete and cancel buttons
        JButton finish = new JButton("Finish");
        JButton delete = new JButton("Delete");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(finish);
        buttons.add(delete);
        buttons.add(cancel);

        // add action listeners for buttons and checkbox
        finish.addActionListener(this);
        delete.addActionListener(this);
        cancel.addActionListener(this);
        recurring.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // add all components to panel
        this.add(screenTitle);
        this.add(titleInfo);
        this.add(prioInfo);
        this.add(dateInfo);
        this.add(startTimeInfo);
        this.add(endTimeInfo);
        this.add(recurring);
        this.add(frequencyInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("Finish")) { // finish edit
                // check if marked complete
                boolean valComplete = complete.isSelected();
                // change to original value if field left blank
                String valTitle = title.getText().equals("") ? eventInfo.getTitle() : title.getText();
                int valPriority = priority.getText().equals("") ? eventInfo.getPriority() : Integer.parseInt(priority.getText());
                LocalDateTime valStartTime = date.getText().equals("") || startTime.getText().equals("") ?
                        eventInfo.getStartTime() : LocalDateTime.parse(date.getText() + "T" + startTime.getText() + ":00");
                LocalDateTime valEndTime = date.getText().equals("") || endTime.getText().equals("") ?
                        eventInfo.getEndTime() : LocalDateTime.parse(date.getText() + "T" + endTime.getText() + ":00");
                boolean valRecurring = recurring.isSelected();
                String valFrequency = "";
                if (valRecurring) valFrequency = frequency.getText();

                // edit event
                eventEditController.edit(valComplete, eventInfo.getId(), valTitle, valPriority, valStartTime, valEndTime, valRecurring, valFrequency);
            } else if (e.getActionCommand().equals("Delete")) { // delete Event
                taskDeletionController.delete(student, eventInfo.getId());
            } else if (e.getActionCommand().equals("Cancel")) { // Edit cancelled
                screenLayout.show(screens, "main");
            } else { // checkbox pressed
                frequency.setEnabled(recurring.isSelected());
            }
        } catch (Exception ex) {
            showMessageDialog(this, ex.getMessage());
        }
    }
}

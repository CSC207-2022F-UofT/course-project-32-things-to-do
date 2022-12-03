package screens.task_management.task_edit_delete_screens.event_edit_delete_screens;

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

    // controllers
    EventEditController eventEditController;
    TaskDeletionController taskDeletionController;

    // to access rest of screens in program
    JPanel screens;
    CardLayout screenLayout;

    // to access info of assignment being edited/deleted
    EventDisplayer eventInfo;

    /**
     * A screen for editing/deleting an Event
     * @param eventEditController - controller for editing use case
     * @param taskDeletionController - controller for deleting use case
     * @param screens - rest of screens in the program
     * @param screenLayout - for switching between screens
     * @param eventInfo - to access info about an Event
     */
    public EventEditDeleteScreen(EventEditController eventEditController,
                                 TaskDeletionController taskDeletionController, JPanel screens,
                                 CardLayout screenLayout, EventDisplayer eventInfo) {
        this.eventEditController = eventEditController;
        this.taskDeletionController = taskDeletionController;
        this.screens = screens;
        this.screenLayout = screenLayout;
        this.eventInfo = eventInfo;

        // set screen title
        JLabel screenTitle = new JLabel("Event Edit/Delete Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // fill all text fields and whatnot
        title = new JTextField(eventInfo.getTitle(), 15);
        title.setEnabled(false);
        priority = new JTextField("" + eventInfo.getPriority(), 15);
        date = new JTextField(eventInfo.getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE), 15);
        startTime = new JTextField(eventInfo.getStartTime().format(DateTimeFormatter.ISO_LOCAL_TIME), 15);
        endTime = new JTextField(eventInfo.getEndTime().format(DateTimeFormatter.ISO_LOCAL_TIME), 15);
        recurring = new JCheckBox("Is the event recurring?", eventInfo.getRecurring());
        frequency = new JTextField(eventInfo.getFrequency(), 15);

        // create labels for all text fields
        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Event title:"), title);
        LabelTextPanel prioInfo = new LabelTextPanel(
                new JLabel("Enter new event priority (integer)"), priority);
        LabelTextPanel dateInfo = new LabelTextPanel(
                new JLabel("Enter new event start date (yyyy-MM-dd)"), date);
        LabelTextPanel startTimeInfo = new LabelTextPanel(
                new JLabel("Enter new event start time (hh:mm, 24 hour)"), startTime);
        LabelTextPanel endTimeInfo = new LabelTextPanel(
                new JLabel("Enter new event end time (hh:mm, 24 hour)"), endTime);
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
        this.add(complete);
        this.add(titleInfo);
        this.add(prioInfo);
        this.add(dateInfo);
        this.add(startTimeInfo);
        this.add(endTimeInfo);
        this.add(recurring);
        this.add(frequencyInfo);
        this.add(buttons);
    }

    /**
     * React to button clicks
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("Finish")) { // finish edit
                // check if marked complete
                boolean valComplete = complete.isSelected();
                // change to original value if field left blank
                int valPriority = priority.getText().equals("") ? eventInfo.getPriority() : Integer.parseInt(priority.getText());
                LocalDateTime valStartTime = date.getText().equals("") || startTime.getText().equals("") ?
                        eventInfo.getStartTime() : LocalDateTime.parse(date.getText() + "T" + startTime.getText());
                LocalDateTime valEndTime = date.getText().equals("") || endTime.getText().equals("") ?
                        eventInfo.getEndTime() : LocalDateTime.parse(date.getText() + "T" + endTime.getText());
                boolean valRecurring = recurring.isSelected();
                String valFrequency = "";
                if (valRecurring) valFrequency = frequency.getText();

                // edit Event
                eventEditController.edit(valComplete, eventInfo.getId(), valPriority, valStartTime, valEndTime, valRecurring, valFrequency);

                // update user and return to main screen
                showMessageDialog(this, "Event edited successfully");
                screenLayout.show(screens, "StudentMain");
            } else if (e.getActionCommand().equals("Delete")) { // delete Event
                // delete Event
                taskDeletionController.delete(eventInfo.getId());

                // update user and return to main screen
                showMessageDialog(this, "Event deleted successfully");
                screenLayout.show(screens, "StudentMain");
            } else if (e.getActionCommand().equals("Cancel")) { // Edit cancelled
                screenLayout.show(screens, "toDoList");
            } else { // checkbox pressed
                frequency.setEnabled(recurring.isSelected());
            }
        } catch (Exception ex) {
            showMessageDialog(this, ex.getMessage());
        }
    }
}

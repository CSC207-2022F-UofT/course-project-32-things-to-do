package screens.task_management.event_creation_screens;

import screens.LabelCheckBox;
import screens.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import static javax.swing.JOptionPane.showMessageDialog;

public class EventCreationScreen extends JPanel implements ActionListener {
    // text fields
    JTextField title = new JTextField(15);
    JTextField priority = new JTextField(15);
    JTextField date = new JTextField(15);
    JTextField startTime = new JTextField(15);
    JTextField endTime = new JTextField(15);
    JCheckBox recurring = new JCheckBox();
    JTextField frequency = new JTextField(15);

    // controller
    EventCreationController eventCreationController;

    // to access rest of screens in program
    JPanel screens;
    CardLayout screenLayout;

    public EventCreationScreen(EventCreationController controller, JPanel screens, CardLayout screenLayout) {
        this.eventCreationController = controller;
        this.screens = screens;
        this.screenLayout = screenLayout;

        JLabel screenTitle = new JLabel("Event Creation Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // create labels for all text fields
        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Enter event title"), title);
        LabelTextPanel prioInfo = new LabelTextPanel(
                new JLabel("Enter event priority (integer)"), priority);
        LabelTextPanel dateInfo = new LabelTextPanel(
                new JLabel("Enter event start date (yyyy-MM-dd)"), date);
        LabelTextPanel startTimeInfo = new LabelTextPanel(
                new JLabel("Enter event start time (hh:mm)"), startTime);
        LabelTextPanel endTimeInfo = new LabelTextPanel(
                new JLabel("Enter event end time (hh:mm)"), endTime);
        LabelCheckBox recurringInfo = new LabelCheckBox(
                new JLabel("Event is recurring"), recurring);
        LabelTextPanel frequencyInfo = new LabelTextPanel(
                new JLabel("Enter frequency of event"), frequency);

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
        this.add(recurringInfo);
        this.add(frequencyInfo);
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
                // get value of recurring check box
                boolean valRecurring = recurring.isSelected();
                // set priority to the value in the box or 0 if blank
                int valPriority = priority.getText().equals("") ? 0 : Integer.parseInt(priority.getText());
                if (valPriority < 0) valPriority = 0;
                // get the start and end date+times and parse them
                LocalDateTime startDate = LocalDateTime.parse(date.getText() + "T" + startTime.getText() + ":00");
                LocalDateTime endDate = LocalDateTime.parse(date.getText() + "T" + endTime.getText() + ":00");

                eventCreationController.create(title.getText(), valPriority,
                        startDate, endDate, valRecurring, frequency.getText());

                showMessageDialog(this, "Event Created Successfully");
            } catch (Exception e) {
                showMessageDialog(this, e.getMessage());
            }
        }
        // if "Cancel" button pressed
        else if (evt.getActionCommand().equals("Cancel")) {
            screenLayout.show(screens, "main");
        }
    }
}

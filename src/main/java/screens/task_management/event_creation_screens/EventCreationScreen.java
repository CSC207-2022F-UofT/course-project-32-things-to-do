package event_creation_screens;

import screens.LabelCheckBox;
import screens.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static javax.swing.JOptionPane.showMessageDialog;

public class EventCreationScreen extends JPanel implements ActionListener {
    // text fields
    JTextField title = new JTextField(15);
    JTextField priority = new JTextField(15);
    // DateFormat df = new SimpleDateFormat("MM-DD-YYYY;HH:mm:ss");
    JTextField startDay = new JTextField(15);
    JTextField startTime = new JTextField(15);
    JTextField endDay = new JTextField(15);
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
        LabelTextPanel startDayInfo = new LabelTextPanel(
                new JLabel("Enter event start date (yyyy-MM-dd)"), startDay);
        LabelTextPanel startTimeInfo = new LabelTextPanel(
                new JLabel("Enter event start time (hh:mm)"), startTime);
        LabelTextPanel endDayInfo = new LabelTextPanel(
                new JLabel("Enter event end date (yyyy-MM-dd"), endDay);
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

        this.add(screenTitle);
        this.add(titleInfo);
        this.add(prioInfo);
        this.add(startDayInfo);
        this.add(startTimeInfo);
        this.add(endDayInfo);
        this.add(endTimeInfo);
        this.add(recurringInfo);
        this.add(frequencyInfo);
        this.add(buttons);
    }

    @Override
    /**
     * Handle button presses
     */
    public void actionPerformed(ActionEvent evt) {
        // if "Finish" button pressed
        if (evt.getActionCommand().equals("Finish")) {
            try {
                // put this in a try catch for debugging issues bc wtf is wrong
                try {
                    // get value of recurring check box
                    boolean valRecurring = recurring.isSelected();
                    // set priority to the value in the box or 0 if blank
                    int valPriority = priority.getText().equals("") ? 0 : Integer.parseInt(priority.getText());
                    if (valPriority < 0) valPriority = 0;
                    // get the start and end date+times and parse them
                    LocalDateTime startDate = LocalDateTime.parse(startDay.getText() + "T" + startTime.getText() + ":00");
                    LocalDateTime endDate = LocalDateTime.parse(endDay.getText() + "T" + endTime.getText() + ":00");

                    eventCreationController.create(title.getText(), valPriority,
                            startDate, endDate, valRecurring, frequency.getText());

                    showMessageDialog(this, "message");
                } catch (Exception e) {
                    showMessageDialog(this, e);
                }
            } catch (Exception e) {
                showMessageDialog(this, e.getMessage());
            }
        }
        // if "Cancel" button pressed
        else if (evt.getActionCommand().equals("Cancel")) {
            System.exit(0);
            screenLayout.show(screens, "main");
        }
    }
}

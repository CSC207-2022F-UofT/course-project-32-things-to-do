package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static javax.swing.JOptionPane.showMessageDialog;

public class EventCreationScreen extends JPanel implements ActionListener {
    JTextField title = new JTextField(15);
    JTextField priority = new JTextField(15);
    DateFormat df = new SimpleDateFormat("MM-DD-YYYY;HH:mm:ss");
    JFormattedTextField startTime = new JFormattedTextField(df);
    JFormattedTextField endTime = new JFormattedTextField(df);
    JCheckBox recurring = new JCheckBox();
    JTextField frequency = new JTextField(15);

    EventCreationController eventCreationController;

    public EventCreationScreen(EventCreationController controller) {
        this.eventCreationController = controller;

        JLabel screenTitle = new JLabel("Event Creation Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Enter event title"), title);
        LabelTextPanel prioInfo = new LabelTextPanel(
                new JLabel("Enter event priority"), priority);
        LabelTextPanel startTimeInfo = new LabelTextPanel(
                new JLabel("Enter event start date+time"), startTime);
        LabelTextPanel endTimeInfo = new LabelTextPanel(
                new JLabel("Enter event end date+time"), endTime);
        LabelCheckBox recurringInfo = new LabelCheckBox(
                new JLabel("Event is recurring"), recurring);
        LabelTextPanel frequencyInfo = new LabelTextPanel(
                new JLabel("Enter frequency of event"), frequency);

        JButton finish = new JButton("Finish");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(finish);
        buttons.add(cancel);

        finish.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(screenTitle);
        this.add(titleInfo);
        this.add(prioInfo);
        this.add(startTimeInfo);
        this.add(endTimeInfo);
        this.add(recurringInfo);
        this.add(frequencyInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Finish")) {
            try {
                boolean valRecurring = recurring.isSelected();
                int valPriority = priority.getText().equals("") ? 0 : Integer.parseInt(priority.getText());
                if (valPriority < 0) valPriority = 0;

                eventCreationController.create(title.getText(), valPriority,
                        LocalDateTime.parse(startTime.getText()), LocalDateTime.parse(endTime.getText()),
                        valRecurring, frequency.getText());
                JOptionPane.showMessageDialog(this, "message");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if (evt.getActionCommand().equals("Cancel")) {
            // todo
        }
    }
}

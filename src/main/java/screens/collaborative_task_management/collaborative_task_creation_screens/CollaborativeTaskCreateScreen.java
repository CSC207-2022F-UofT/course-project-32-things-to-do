package screens.collaborative_task_management.collaborative_task_creation_screens;

import screens.LabelTextPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import static javax.swing.JOptionPane.showMessageDialog;

public class CollaborativeTaskCreateScreen extends JPanel implements ActionListener {
    JTextField title = new JTextField(15);
    JTextField priority = new JTextField(15);
    JCheckBox recurring = new JCheckBox("Is the collaborative task recurring?");
    JTextField frequency = new JTextField(15);
    JTextField startDate = new JTextField(15);
    JTextField startTime = new JTextField(15);
    JTextField endDate = new JTextField(15);
    JTextField endTime = new JTextField(15);
    JTextField deadlineDate = new JTextField(15);
    JTextField deadlineTime = new JTextField(15);

    CollaborativeTaskCreationController collaborativeTaskCreationController;

    JPanel screens;

    CardLayout screenLayout;

    public CollaborativeTaskCreateScreen(CollaborativeTaskCreationController controller, JPanel screens, CardLayout screenLayout) {
        this.collaborativeTaskCreationController = controller;
        this.screens = screens;
        this.screenLayout = screenLayout;

        JLabel screenTitle = new JLabel("Collaborative Task Creation Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel titleInfo = new LabelTextPanel(new JLabel("Enter collaborative task title"), title);
        LabelTextPanel priorityInfo = new LabelTextPanel(new JLabel("Enter collaborative task priority (integer)"), priority);
        LabelTextPanel frequencyInfo = new LabelTextPanel(new JLabel("Enter frequency of collaborative event (\"daily\", \"weekly\", \"monthly\")"), frequency);
        frequency.setEnabled(false);
        LabelTextPanel startDateInfo = new LabelTextPanel(new JLabel("Enter collaborative task start date (yyyy-mm-dd)"), startDate);
        LabelTextPanel startTimeInfo = new LabelTextPanel(new JLabel("Enter collaborative task start time (hh:mm, 24 hour)"), startDate);
        LabelTextPanel endDateInfo = new LabelTextPanel(new JLabel("Enter collaborative task end date (yyyy-mm-dd)"), endDate);
        LabelTextPanel endTimeInfo = new LabelTextPanel(new JLabel("Enter collaborative task end time (hh:mm, 24 hour)"), endTime);
        LabelTextPanel deadlineDateInfo = new LabelTextPanel(new JLabel("Enter collaborative task deadline date (yyyy-mm-dd)"), deadlineDate);
        LabelTextPanel deadlineTimeInfo = new LabelTextPanel(new JLabel("Enter collaborative task deadline time (hh:mm, 24 hour)"), deadlineTime);

        JButton finish = new JButton("Finish");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(finish);
        buttons.add(cancel);

        finish.addActionListener(this);
        cancel.addActionListener(this);

        recurring.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(screenTitle);
        this.add(titleInfo);
        this.add(priorityInfo);
        this.add(recurring);
        this.add(frequencyInfo);
        this.add(startDateInfo);
        this.add(startTimeInfo);
        this.add(endDateInfo);
        this.add(endTimeInfo);
        this.add(endTimeInfo);
        this.add(deadlineDateInfo);
        this.add(deadlineTimeInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt){
        if (evt.getActionCommand().equals("Finish")){
            try {
                int valPriority = priority.getText().equals("") ? 0 : Integer.parseInt(priority.getText());
                if (valPriority < 0) valPriority = 0;
                boolean valRecurring = recurring.isSelected();
                String valFrequency = "";
                if (valRecurring) valFrequency = frequency.getText();
                LocalDateTime startDateTime = LocalDateTime.parse(startDate.getText() + "T" + startTime.getText());
                LocalDateTime endDateTime = LocalDateTime.parse(endDate.getText() + "T" + endTime.getText());
                LocalDateTime deadline = LocalDateTime.parse(deadlineDate.getText() + "T" + deadlineTime.getText());

                collaborativeTaskCreationController.create(title.getText(), valPriority, valRecurring, valFrequency, startDateTime, endDateTime, deadline);

                showMessageDialog(this, "Collaborative Task Created Successfully");
                screenLayout.show(screens, "StudentMain");
            } catch (Exception e) {
                showMessageDialog(this, e.getMessage());
            }
        }
        else if (evt.getActionCommand().equals("Cancel")){
            screenLayout.show(screens, "StudentMain");
        }
        else {
            frequency.setEnabled(recurring.isSelected());
        }
    }
}

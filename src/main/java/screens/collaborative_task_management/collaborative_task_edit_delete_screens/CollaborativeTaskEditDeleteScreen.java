package screens.collaborative_task_management.collaborative_task_edit_delete_screens;

import use_cases.collaborative_task_management.collaborative_task_edit_use_case.CollaborativeTaskDisplayer;

import screens.LabelTextPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static javax.swing.JOptionPane.showMessageDialog;

public class CollaborativeTaskEditDeleteScreen extends JPanel implements ActionListener {
    JCheckBox complete = new JCheckBox(("Mark collaborative task complete?"));
    JTextField title;
    JTextField priority;
    JCheckBox recurring;
    JTextField frequency;
    JTextField startDate;
    JTextField startTime;
    JTextField endDate;
    JTextField endTime;
    JTextField deadlineDate;
    JTextField deadlineTime;

    CollaborativeTaskEditController collaborativeTaskEditController;
    CollaborativeTaskDeletionController collaborativeTaskDeletionController;

    JPanel screens;
    CardLayout screenLayout;

    CollaborativeTaskDisplayer collaborativeTaskInfo;

    /**
     * A screen for editing/deleting an Event
     * @param collaborativeTaskEditController - controller for editing use case
     * @param collaborativeTaskDeletionController - controller for deleting use case
     * @param screens - rest of screens in the program
     * @param screenLayout - for switching between screens
     * @param collaborativeTaskInfo - to access info about a Collaborative Task
     */
    public CollaborativeTaskEditDeleteScreen(CollaborativeTaskEditController collaborativeTaskEditController, CollaborativeTaskDeletionController collaborativeTaskDeletionController, JPanel screens, CardLayout screenLayout, CollaborativeTaskDisplayer collaborativeTaskInfo) {
        this.collaborativeTaskEditController = collaborativeTaskEditController;
        this.collaborativeTaskDeletionController = collaborativeTaskDeletionController;
        this.screens = screens;
        this.screenLayout = screenLayout;
        this.collaborativeTaskInfo = collaborativeTaskInfo;

        JLabel screenTitle = new JLabel("Collaborative Task Edit/Delete Screen");
        screenTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        title = new JTextField(collaborativeTaskInfo.getTitle(), 15);
        title.setEnabled(false);
        priority = new JTextField("" + collaborativeTaskInfo.getPriority(), 15);
        recurring = new JCheckBox("Is the event recurring?", collaborativeTaskInfo.isRecurring());
        frequency = new JTextField(collaborativeTaskInfo.getFrequency(), 15);
        startDate = new JTextField(collaborativeTaskInfo.getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE), 15);
        startTime = new JTextField(collaborativeTaskInfo.getStartTime().format(DateTimeFormatter.ISO_LOCAL_TIME), 15);
        endDate = new JTextField(collaborativeTaskInfo.getEndTime().format(DateTimeFormatter.ISO_LOCAL_DATE), 15);
        endTime = new JTextField(collaborativeTaskInfo.getEndTime().format(DateTimeFormatter.ISO_LOCAL_TIME), 15);
        deadlineDate = new JTextField(collaborativeTaskInfo.getDeadline().format(DateTimeFormatter.ISO_LOCAL_DATE), 15);
        deadlineTime = new JTextField(collaborativeTaskInfo.getDeadline().format(DateTimeFormatter.ISO_LOCAL_TIME), 15);


        LabelTextPanel titleInfo = new LabelTextPanel(new JLabel("Collaborative Task title"), title);
        LabelTextPanel priorityInfo = new LabelTextPanel(new JLabel("Enter new collaborative task priority (integer)"), priority);
        LabelTextPanel frequencyInfo = new LabelTextPanel(new JLabel("Enter new frequency of collaborative event (\"daily\", \"weekly\", \"monthly\")"), frequency);
        frequency.setEnabled(recurring.isSelected());
        LabelTextPanel startDateInfo = new LabelTextPanel(new JLabel("Enter new collaborative task start date (yyyy-mm-dd)"), startDate);
        LabelTextPanel startTimeInfo = new LabelTextPanel(new JLabel("Enter new collaborative task start time (hh:mm, 24 hour)"), startDate);
        LabelTextPanel endDateInfo = new LabelTextPanel(new JLabel("Enter new collaborative task end date (yyyy-mm-dd)"), endDate);
        LabelTextPanel endTimeInfo = new LabelTextPanel(new JLabel("Enter new collaborative task end time (hh:mm, 24 hour)"), endTime);
        LabelTextPanel deadlineDateInfo = new LabelTextPanel(new JLabel("Enter new collaborative task deadline date (yyyy-mm-dd)"), deadlineDate);
        LabelTextPanel deadlineTimeInfo = new LabelTextPanel(new JLabel("Enter new collaborative task deadline time (hh:mm, 24 hour)"), deadlineTime);

        JButton finish = new JButton("Finish");
        JButton delete = new JButton("Delete");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(finish);
        buttons.add(delete);
        buttons.add(cancel);

        finish.addActionListener(this);
        delete.addActionListener(this);
        cancel.addActionListener(this);
        recurring.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(screenTitle);
        this.add(complete);
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
    public void actionPerformed(ActionEvent evt) {
        try {
            if (evt.getActionCommand().equals("Finish")){
                boolean valComplete = complete.isSelected();
                int valPriority = priority.getText().equals("") ? collaborativeTaskInfo.getPriority() : Integer.parseInt(priority.getText());
                if (valPriority < 0) valPriority = 0;
                boolean valRecurring = recurring.isSelected();
                String valFrequency = "";
                if (valRecurring) valFrequency = frequency.getText();
                LocalDateTime valStartDateTime = startDate.getText().equals("") || startTime.getText().equals("") ? collaborativeTaskInfo.getStartTime() : LocalDateTime.parse(startDate.getText() + "T" + startTime.getText());
                LocalDateTime valEndDateTime = endDate.getText().equals("") || endTime.getText().equals("") ? collaborativeTaskInfo.getEndTime() : LocalDateTime.parse(endDate.getText() + "T" + endTime.getText());
                LocalDateTime valDeadline = deadlineDate.getText().equals("") || deadlineTime.getText().equals("") ? collaborativeTaskInfo.getDeadline() : LocalDateTime.parse(deadlineDate.getText() + "T" + deadlineTime.getText());

                collaborativeTaskEditController.edit(valComplete, collaborativeTaskInfo.getId(), valPriority, valRecurring, valFrequency, valStartDateTime, valEndDateTime, valDeadline);

                showMessageDialog(this, "Collaborative Task Edited Successfully");
                screenLayout.show(screens, "StudentMain");
            }
            else if (evt.getActionCommand().equals("Delete")){
                collaborativeTaskDeletionController.delete(collaborativeTaskInfo.getId());
                showMessageDialog(this, "Collaborative Task Deleted Successfully");
                screenLayout.show(screens, "StudentMain");
            }
            else if (evt.getActionCommand().equals("Cancel")){
                screenLayout.show(screens, "toDoList");
            }
            else {
                frequency.setEnabled(recurring.isSelected());
            }
        } catch (Exception e) {
            showMessageDialog(this, e.getMessage());
        }
    }
}

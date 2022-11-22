package screens;

import progress_tracker_use_case.ProgressTrackerResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the View for Progress Tracker Use Case.
 */

public class ProgressTrackerScreen extends JPanel implements ActionListener {

    JLabel title = new JLabel("Course View Page");

    JLabel progressTrackerOutput = new JLabel("");

    JTextField courseName = new JTextField(15);
    JTextField newGradeTaskName = new JTextField(15);
    JTextField newGrade = new JTextField(15);
    JTextField newGoalGrade = new JTextField(15);

    ProgressTrackerController progressTrackerController;

    public ProgressTrackerScreen(ProgressTrackerController controller) {

        this.progressTrackerController = controller;

        JLabel instructions1 = new JLabel("1. See Your Course Progress");
                LabelTextPanel courseNameInfo = new LabelTextPanel(
                new JLabel("Enter the name of a course"), courseName);

        JLabel instructions2 = new JLabel("2. Enter Course Progress Data");
        LabelTextPanel newGradeTaskNameInfo = new LabelTextPanel(
                new JLabel("Enter the name of the task for the new received grade"), newGradeTaskName);
        LabelTextPanel newGradeInfo = new LabelTextPanel(
                new JLabel("Enter the new received grade"), newGrade);
        LabelTextPanel newGoalGradeInfo = new LabelTextPanel(
                new JLabel("Enter new desired grade for this course"), newGoalGrade);

        JButton open = new JButton("Calculate Grades and Progress");
        JButton enter = new JButton("Save and Update");

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions1.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions2.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressTrackerOutput.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel button1 = new JPanel();
        button1.add(open);
        JPanel button2 = new JPanel();
        button2.add(enter);

        open.addActionListener(this);
        enter.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(progressTrackerOutput);
        this.add(instructions1);
        this.add(courseNameInfo);
        this.add(button1);
        this.add(instructions2);
        this.add(newGradeTaskNameInfo);
        this.add(newGradeInfo);
        this.add(newGoalGradeInfo);
        this.add(button2);

    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        try {
            //usually the presenter would be updating the view, but in this case, the presenter will
            // eventually be called
            //TODO might need to convert controller to presenter later
            ProgressTrackerResponseModel rsp = progressTrackerController.trackProgress(courseName.getText(),
                    newGradeTaskName.getText(), newGrade.getText(), newGoalGrade.getText());

            title.setText(courseName.getText() + " Course View Page");
            progressTrackerOutput.setText(rsp.getDisplayString());

            validate();
            repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

}

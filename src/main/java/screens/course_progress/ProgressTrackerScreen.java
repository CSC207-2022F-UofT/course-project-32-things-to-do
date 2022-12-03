package screens.course_progress;

import screens.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the View for Progress Tracker Use Case.
 */

public class ProgressTrackerScreen extends JPanel implements ActionListener, ProgressTrackerViewBoundary {
    //JComponents for screen output
    JLabel title = new JLabel("Course View Page");
    JTextPane progressTrackerOutput = new JTextPane();

    //JComponents for screen input
    JTextField courseName = new JTextField(15);
    JTextField newGradeTaskName = new JTextField(15);
    JTextField newGrade = new JTextField(15);
    JTextField newGoalGrade = new JTextField(15);

    //injection for clean architecture
    ProgressTrackerController progressTrackerController;

    //buttons
    JButton open;
    JButton enter;
    JButton back;

    //for switching screens
    JPanel screens;
    CardLayout cardLayout;

    //instance variables for storing information
    ArrayList<String> ungradedTasks;
    double mockGrade;

    public ProgressTrackerScreen(JPanel screens, CardLayout cardLayout) {

        this.screens = screens;
        this.cardLayout = cardLayout;

        JLabel instructions1 = new JLabel("1. See Your Course Progress");
        LabelTextPanel courseNameInfo = new LabelTextPanel(new JLabel("Enter the name of a course"), courseName);

        JLabel instructions2 = new JLabel("2. Enter Course Progress Data");
        LabelTextPanel newGradeTaskNameInfo = new LabelTextPanel(
                new JLabel("Enter the name of the task for the new received grade"), newGradeTaskName);
        LabelTextPanel newGradeInfo = new LabelTextPanel(
                new JLabel("Enter the new received grade"), newGrade);
        LabelTextPanel newGoalGradeInfo = new LabelTextPanel(
                new JLabel("Enter new desired grade for this course"), newGoalGrade);

        open = new JButton("Calculate Grades and Progress");
        enter = new JButton("Save and Update");
        back = new JButton("Return");

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions1.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions2.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressTrackerOutput.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressTrackerOutput.setEditable(false);
        progressTrackerOutput.setVisible(false);

        JPanel button1 = new JPanel();
        button1.add(open);
        JPanel button2 = new JPanel();
        button2.add(enter);
        JPanel button3 = new JPanel();
        button3.add(back);

        open.addActionListener(this);
        enter.addActionListener(this);
        back.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(instructions1);
        this.add(courseNameInfo);
        this.add(button1);
        this.add(progressTrackerOutput);
        this.add(instructions2);
        this.add(newGradeTaskNameInfo);
        this.add(newGradeInfo);
        this.add(newGoalGradeInfo);
        this.add(button2);
        this.add(button3);

    }

    public void setProgressTrackerController(ProgressTrackerController controller) {
        this.progressTrackerController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        if (evt.getSource() == open || evt.getSource() == enter) {
            try {
                progressTrackerController.trackProgress(courseName.getText(),
                        newGradeTaskName.getText(), newGrade.getText(), newGoalGrade.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if (evt.getSource() == back) {
            cardLayout.show(screens, "StudentMain");
        }
    }

    @Override
    public void dispaly(ProgressTrackerViewModel viewModel) {
        title.setText(courseName.getText() + " Course View Page");

        ungradedTasks = viewModel.getUngradedTasks();
        mockGrade = viewModel.getMockGrade();

        progressTrackerOutput.setText(viewModel.getDisplayString());
        progressTrackerOutput.setPreferredSize(new Dimension(200, 150));
        progressTrackerOutput.setVisible(true);

        validate();
        repaint();
    }
}

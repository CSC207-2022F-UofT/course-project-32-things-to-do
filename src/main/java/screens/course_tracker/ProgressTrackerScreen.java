package screens.course_tracker;

import screens.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the View for Progress Tracker Use Case.
 */
public class ProgressTrackerScreen extends JPanel implements ActionListener, ProgressTrackerViewBoundary,
        GradeCalculatorViewBoundary{

    //JComponents for screen output
    JLabel title = new JLabel("Course View Page");
    JTextPane progressTrackerOutput = new JTextPane();

    //JComponents for screen input
    JTextField courseName = new JTextField(15);
    JTextField newGradeTaskName = new JTextField(15);
    JTextField newGrade = new JTextField(15);
    JTextField newGoalGrade = new JTextField(15);
    JTextField sandboxInput = new JTextField(15);

    //injection for clean architecture
    ProgressTrackerController progressTrackerController;
    GradeCalculatorController gradeCalculatorController;

    //buttons
    JButton open;
    JButton enter;
    JButton calculate;
    JButton back;

    //for switching screens
    JPanel screens;
    CardLayout cardLayout;

    //instance variables for storing information
    ArrayList<String> ungradedTasks = null;

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
                new JLabel("Enter new desired grade for this course (optional)"), newGoalGrade);

        JLabel instructions3 = new JLabel("3. GRADE SANDBOX!");
        JTextArea instructions3details = new JTextArea(1,20);
        instructions3details.setText(" Find out the required (average) grade of some ungraded target task(s) " +
                "given your goal/predicted grades for the rest of your ungraded tasks.\n Input should be in the " +
                "following example form:\n 89,*96,77.7,36,*97\n where '*' indicates your goal grade in the " +
                "course. The program will take the value of the LAST * value if they are different.\n Also note that" +
                " the total task weightage per course should be 100%.");
        instructions3details.setEditable(false);
        LabelTextPanel gradeCalculatorInput = new LabelTextPanel(
                new JLabel("Enter your sanbox input"), sandboxInput);

        open = new JButton("Calculate Grades and Progress");
        enter = new JButton("Save and Update");
        calculate = new JButton("Calculate");
        back = new JButton("Return");

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions1.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions2.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions3.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions3details.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressTrackerOutput.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressTrackerOutput.setEditable(false);
        progressTrackerOutput.setVisible(false);

        JPanel button1 = new JPanel();
        button1.add(open);
        JPanel button2 = new JPanel();
        button2.add(enter);
        JPanel button3 = new JPanel();
        button3.add(calculate);
        JPanel button4 = new JPanel();
        button4.add(back);

        open.addActionListener(this);
        enter.addActionListener(this);
        back.addActionListener(this);
        calculate.addActionListener(this);

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
        this.add(instructions3);
        this.add(instructions3details);
        this.add(gradeCalculatorInput);
        this.add(button3);
        this.add(button4);

    }

    public void setProgressTrackerController(ProgressTrackerController controller) {
        this.progressTrackerController = controller;
    }

    public void setGradeCalculatorController(GradeCalculatorController gradeCalculatorController) {
        this.gradeCalculatorController = gradeCalculatorController;
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
        } else if (evt.getSource() == calculate) {
            try {
                gradeCalculatorController.calculateGrade(courseName.getText(), sandboxInput.getText(), ungradedTasks);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if (evt.getSource() == back) {
            cardLayout.show(screens, "StudentMain");
        }
    }

    @Override
    public void display(ProgressTrackerViewModel viewModel) {
        title.setText(courseName.getText() + " Course View Page");

        ungradedTasks = viewModel.getUngradedTasks();

        progressTrackerOutput.setText(viewModel.getDisplayString());
        progressTrackerOutput.setPreferredSize(new Dimension(200, 150));
        progressTrackerOutput.setVisible(true);

        validate();
        repaint();
    }

    @Override
    public void showRequiredGrade(GradeCalculatorViewModel viewModel) {
        JOptionPane.showMessageDialog(this, viewModel.getDisplayMessage());
    }
}

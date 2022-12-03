package screens.course_features;

// Framework / Drivers layer

import screens.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseCreationScreen extends JPanel implements ActionListener {
    /** the course name chosen by InstructorUser */
    JTextField courseName = new JTextField(15);

    /** the course instructor */
    JTextField courseInstructor = new JTextField(15);

    /** title of task */
    JTextField taskNames = new JTextField(15);

    /** the controller */
    CourseCreationController courseCreationController;

    /**
     * Objects for connecting to the other screens
     */
    JPanel screens;
    CardLayout screenLayout;

    /**
     * A window with a title, texts to fill in, and JButtons
     */
    public CourseCreationScreen(CourseCreationController controller, JPanel screens, CardLayout screenLayout) {
        this.courseCreationController = controller;
        this.screens = screens;
        this.screenLayout = screenLayout;

        // label for the title of the screen
        JLabel title = new JLabel("Course Creation Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // text fields for instructor to enter in required course info
        LabelTextPanel courseNameInfo = new LabelTextPanel(
                new JLabel("Enter course name"), courseName);
        LabelTextPanel courseInstructorInfo = new LabelTextPanel(
                new JLabel("Enter instructor name"), courseInstructor);
        LabelTextPanel taskNameInfo = new LabelTextPanel(
                new JLabel("Enter task title(s), separated by a comma"), taskNames);

        // buttons
        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save");

        JPanel buttons = new JPanel();
        buttons.add(cancel);
        buttons.add(save);

        cancel.addActionListener(this);
        save.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // create the new panel
        this.add(title);
        this.add(courseNameInfo);
        this.add(courseInstructorInfo);
        this.add(taskNameInfo);
        this.add(buttons);
    }

    /**
     * React to a button click which triggers the corresponding use case
     * have to keep try catch, or else error messages won't show up
     * NEED TO FIX THIS!!!
     * 1. loop through tasks + append
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // instructor decides to cancel the course creation process
        if (evt.getActionCommand().equals("Cancel")) {
            screenLayout.show(screens, "InstructorMain");
        } else if (evt.getActionCommand().equals("Save")) {
            try {
                // tasksNames right now is an arraylist of ONE string with the string being "task1, task2"
                // split string so that each task is a string
                String[] tasksSplit = taskNames.getText().split(",");
                List<String> taskArrayList;
                taskArrayList = Arrays.asList(tasksSplit);

                // tasklist should be arraylist ["task1", "task2"]
                // add all strings to arraylist 'tasks'
                ArrayList<String> tasks = new ArrayList<>(taskArrayList);

                courseCreationController.create(courseName.getText(), courseInstructor.getText(),
                        tasks);
                JOptionPane.showMessageDialog(
                        this, "Course" + courseName.getText() + "and tasks" + tasks + "successfully created.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
}

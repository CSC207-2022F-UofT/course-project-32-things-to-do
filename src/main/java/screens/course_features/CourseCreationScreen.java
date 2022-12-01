package screens.course_features;

// Framework / Drivers layer

import screens.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
     * NEED TO FIX THIS!!!
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // instructor decides to cancel the course creation process
        if (evt.getActionCommand().equals("Cancel")) {
            screenLayout.show(screens, "main");
        } else if (evt.getActionCommand().equals("Save")) {
                // initialize new Arraylist and add task
                ArrayList<String> tasks = new ArrayList<>();
                tasks.add(taskNames.getText());
                courseCreationController.create(courseName.getText(), courseInstructor.getText(),
                        tasks);
                JOptionPane.showMessageDialog(this, "Course successful created.");
        }
    }
}

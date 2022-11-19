package screens;

// Framework / Drivers layer

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateCourseScreen extends JPanel implements ActionListener {
    /** the course name chosen by InstructorUser */
    JTextField courseName = new JTextField(15);

    /** the course instructor */
    JTextField courseInstructor = new JTextField(15);

    /* the part i don't understand ... tasks .... */

    /** title of task */
    JTextField taskName = new JTextField(25);

    /** id? of task */
    JTextField taskID = new JTextField(25);

    /** the controller */
    CourseCreationController courseCreationController;

    /**
     * A window with a title, texts to fill in, and JButtons */
    public CreateCourseScreen(CourseCreationController controller) {
        this.courseCreationController = controller;

        JLabel title = new JLabel("Course Creation Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel courseNameInfo = new LabelTextPanel(
                new JLabel("Enter course name"), courseName);
        LabelTextPanel courseInstructorInfo = new LabelTextPanel(
                new JLabel("Enter instructor name"), courseInstructor);
        LabelTextPanel taskNameInfo = new LabelTextPanel(
                new JLabel("Enter task title"), taskName);
        LabelTextPanel taskIDInfo = new LabelTextPanel(
                new JLabel("Enter task id"), taskID);

        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save");

        JPanel buttons = new JPanel();
        buttons.add(cancel);
        buttons.add(save);

        cancel.addActionListener(this);
        save.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(courseNameInfo);
        this.add(courseInstructorInfo);
        this.add(taskNameInfo);
        this.add(taskIDInfo);
        this.add(buttons);
    }

    /**
     * React to a button click ...
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        /** why no work :,( */
        try {
            ArrayList<String> task = new ArrayList<String>();
            task.add("taskid");
            courseCreationController.create(courseName.getText(),
                    courseInstructor.getText(), task); // third argument should be taskName.getText()
            JOptionPane.showMessageDialog(this, String.format("%s created.".format(courseName.getText())));
//            courseCreationController.create(courseName.getText(),
//                    courseInstructor.getText(), tasks.getText());
//            JOptionPane.showMessageDialog(this, String.format("%s created.".format(courseName.getText())));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }
}

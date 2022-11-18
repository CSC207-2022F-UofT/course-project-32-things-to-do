package screens;

// Framework / Drivers layer

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCourseScreen extends JPanel implements ActionListener {
    /** the course name chosen by InstructorUser */
    JTextField courseName = new JTextField(15);

    /** the course instructor */
    JTextField courseInstructor = new JTextField(15);

    /** the part i don't understand ... tasks .... */
//    JList tasks = new JList(15);


    /** the controller */
    CourseCreationController courseCreationController;

    /**
     * A window with a title and JButton */
    public CreateCourseScreen(CourseCreationController controller) {
        this.courseCreationController = controller;

        JLabel title = new JLabel("Course Creation Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel courseNameInfo = new LabelTextPanel(
                new JLabel("Enter course name"), courseName);
        LabelTextPanel courseInstructorInfo = new LabelTextPanel(
                new JLabel("Enter instructor name"), courseInstructor);

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
        this.add(buttons);

    }

    /**
     * React to a button click ...
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

//        try {
//            courseCreationController.create(courseName.getText(),
//                    courseInstructor.getText(), tasks.getText());
//            JOptionPane.showMessageDialog(this, String.format("%s created.".format(courseName.getText())));
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
    }


}

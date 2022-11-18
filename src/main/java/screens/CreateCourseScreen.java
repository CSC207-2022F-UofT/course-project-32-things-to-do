package screens;

// Framework / Drivers layer

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCourseScreen extends JPanel implements ActionListener {
    /* the course name chosen by InstructorUser */
    JTextField courseName = new JTextField(15);

    /* the course instructor */
    JTextField courseInstructor = new JTextField(15);

    /* the part i don't understand ... tasks .... */

    /* the controller */
    CourseCreationController courseCreationController;

    /**
     * A window with a title and JButton
     */
    public CreateCourseScreen(CourseCreationController controller) {
        this.courseCreationController = controller;
    }

    /**
     * React to a button click ...
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }


}

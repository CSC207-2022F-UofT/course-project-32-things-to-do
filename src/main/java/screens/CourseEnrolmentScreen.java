package screens;

// Framework / Drivers layer

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseEnrolmentScreen extends JPanel implements ActionListener {
    /** student enters course name */
    JTextField courseName = new JTextField(15);

    /** student enters course instructor */
    JTextField courseInstructor = new JTextField(15);

    /** the controller */
    CourseEnrolmentController courseEnrolmentController;

    /**
     * Window with title and JButtons
     */
    public CourseEnrolmentScreen(CourseEnrolmentController controller) {
        this.courseEnrolmentController = controller;

        JLabel title = new JLabel("Course Enrolment Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel courseNameInfo = new LabelTextPanel(
                new JLabel("Enter course name"), courseName);
        LabelTextPanel courseInstructorInfo = new LabelTextPanel(
                new JLabel("Enter instructor name"), courseInstructor);

        JButton cancel = new JButton("Cancel");
        JButton search = new JButton("Search");

        JPanel buttons = new JPanel();
        buttons.add(cancel);
        buttons.add(search);

        cancel.addActionListener(this);
        search.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(courseNameInfo);
        this.add(courseInstructorInfo);
        this.add(buttons);
    }

    /**
     * reacting to button clicks?
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        try {
//            courseEnrolmentController.cre
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}

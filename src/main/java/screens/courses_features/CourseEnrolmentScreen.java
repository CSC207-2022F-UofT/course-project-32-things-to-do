package screens.courses_features;

// Framework/Drivers layer

import screens.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CourseEnrolmentScreen extends JPanel implements ActionListener {
    /** student enters course name */
    JTextField courseName = new JTextField(15);

    /** student enters course instructor */
    JTextField courseInstructor = new JTextField(15);

    /** student enters their username (aka student id) */
    JTextField studentID = new JTextField(15);

    /** the controller */
    CourseEnrolmentController courseEnrolmentController;

    /**
     * Window with title, texts to fill in, and JButtons
     */
    public CourseEnrolmentScreen(CourseEnrolmentController controller) {
        this.courseEnrolmentController = controller;

        // label for the title of the screen
        JLabel title = new JLabel("Course Enrolment Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // text fields for student user to enter in required info
        LabelTextPanel courseNameInfo = new LabelTextPanel(
                new JLabel("Enter course name"), courseName);
        LabelTextPanel courseInstructorInfo = new LabelTextPanel(
                new JLabel("Enter instructor name"), courseInstructor);
        LabelTextPanel studentIDInfo = new LabelTextPanel(
                new JLabel("Enter instructor name"), studentID);

        // buttons
        JButton cancel = new JButton("Cancel");
        JButton search = new JButton("Search");

        JPanel buttons = new JPanel();
        buttons.add(cancel);
        buttons.add(search);

        cancel.addActionListener(this);
        search.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // create the new panel
        this.add(title);
        this.add(courseNameInfo);
        this.add(courseInstructorInfo);
        this.add(studentIDInfo);
        this.add(buttons);
    }

    /**
     * React to a button click which triggers the corresponding use case
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // student user decides to cancel course enrolment process
        if (evt.getActionCommand().equals("Cancel")) {
            try {
                // do to
                JOptionPane.showMessageDialog(this, "screen should close");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if (evt.getActionCommand().equals("Search")) {
            try {
                // add studentID to course's task parameter?

                courseEnrolmentController.enrol(courseName.getText(),courseInstructor.getText(), studentID.getText());

                // add course tasks to student's to do list?

                JOptionPane.showMessageDialog(this, "Successfully enrolled in course.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
}

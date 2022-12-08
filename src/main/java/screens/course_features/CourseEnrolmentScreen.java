package screens.course_features;

// Framework/Drivers layer

import screens.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
     * Objects for connecting to the other screens
     */
    JPanel screens;
    CardLayout screenLayout;

    /**
     * A window with title, text fields to fill in, and JButtons
     */
    public CourseEnrolmentScreen(CourseEnrolmentController controller, JPanel screens, CardLayout screenLayout) {
        this.courseEnrolmentController = controller;
        this.screens = screens;
        this.screenLayout = screenLayout;

        // label for the title of the screen
        JLabel title = new JLabel("Course Enrolment Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // text fields for student user to enter in required info
        LabelTextPanel courseNameInfo = new LabelTextPanel(
                new JLabel("Enter course name"), courseName);
        LabelTextPanel courseInstructorInfo = new LabelTextPanel(
                new JLabel("Enter instructor name"), courseInstructor);
//        LabelTextPanel studentIDInfo = new LabelTextPanel(
//                new JLabel("Enter your username"), studentID);

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
        this.add(buttons);
    }

    /**
     * React to a button click which triggers the corresponding use case
     * this probably also needs to be fixed!!!
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // student user decides to cancel course enrolment process
        if (evt.getActionCommand().equals("Cancel")) {
            screenLayout.show(screens, "StudentMain");
        } else if (evt.getActionCommand().equals("Search")) {
            try {
                // add student id to Course
                // add course tasks to Student's todolist
                // add course id to Student's courses list

                courseEnrolmentController.enrol(courseName.getText(), courseInstructor.getText());
                JOptionPane.showMessageDialog(this,
                        "Successfully enrolled in course " + courseName.getText() + " with instructor" +
                                courseInstructor.getText() + ". Check your to-do list for the course tasks!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
}

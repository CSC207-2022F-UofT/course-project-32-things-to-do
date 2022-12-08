package screens;

import screens.login_registration.LoginFailed;
import screens.login_registration.LogoutController;
import screens.login_registration.LogoutFailed;
import use_cases.login_registration.login_usecase.LoginResponseModel;
import use_cases.login_registration.logout_usecase.LogoutResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class InstructorMainScreen extends JPanel implements ActionListener {


    /**
     * The selectable buttons on the main screen
     */
    JButton taskCreate;
    JButton calendar;
    JButton courses;
    JButton scheduleCT;

    JButton logout;

    /**
     * Objects for connecting to the other screens
     */
    CardLayout cardLayout;
    JPanel screens;

    LogoutController logoutController;

    /**
     * The window of the main screen with buttons connecting to each use case
     */
    public InstructorMainScreen(JPanel screens, CardLayout cardLayout, LogoutController controller) {

        this.cardLayout = cardLayout;
        this.screens = screens;
        this.logoutController = controller;

        // Create label for title of screen
        JLabel title = new JLabel("32 Things To Do");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create buttons
        taskCreate = new JButton("New Task");
        calendar = new JButton("Calendar");
        courses = new JButton("Courses");
        logout = new JButton("Logout");

        taskCreate.addActionListener(this);
        calendar.addActionListener(this);
        courses.addActionListener(this);
        logout.addActionListener(this);

        // Create panel for buttons
        JPanel buttons = new JPanel();
        buttons.add(taskCreate);
        buttons.add(calendar);
        buttons.add(courses);
        buttons.add(logout);

        // Add all components to the panel
        this.add(title);
        this.add(buttons);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Trigger the corresponding use case upon button click
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == taskCreate) {
            cardLayout.show(screens, "instructorTaskCreate");
        }
        if (evt.getSource() == calendar) {
            cardLayout.show(screens, "calendar");
        }

        if (evt.getSource() == courses) {
            cardLayout.show(screens, "courseCreate");
        }

        if (evt.getSource() == logout) {
            try {
                logoutController.create();
                showMessageDialog(this, "Successfully logged out");
                cardLayout.show(screens, "welcome");
            } catch (Exception e) {
                showMessageDialog(this, "Logout failed");
            }

        }

    }
}

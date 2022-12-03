package screens;

import screens.login_registration.LogoutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMainScreen extends JPanel implements ActionListener {

    /**
     * The selectable buttons on the main screen
     */
    JButton toDoList;
    JButton calendar;
    JButton progressTracker;
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
    public StudentMainScreen(JPanel screens, CardLayout cardLayout, LogoutController controller) {

        this.cardLayout = cardLayout;
        this.screens = screens;
        this.logoutController = controller;

        // Create label for title of screen
        JLabel title = new JLabel("32 Things To Do");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create buttons
        toDoList = new JButton("New Task");
        calendar = new JButton("Calendar");
        progressTracker = new JButton("Progress Tracker");
        courses = new JButton("Courses");
        scheduleCT = new JButton("Schedule Collaborative Task");
        logout = new JButton("Logout");

        toDoList.addActionListener(this);
        calendar.addActionListener(this);
        progressTracker.addActionListener(this);
        courses.addActionListener(this);
        scheduleCT.addActionListener(this);
        logout.addActionListener(this);

        // Create panel for buttons
        JPanel buttons = new JPanel();
        buttons.add(toDoList);
        buttons.add(calendar);
        buttons.add(progressTracker);
        buttons.add(courses);
        buttons.add(scheduleCT);
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
        if (evt.getSource() == toDoList) {
            cardLayout.show(screens, "toDoList");
        }
        if (evt.getSource() == calendar) {
            cardLayout.show(screens, "calendar");
        }
        if (evt.getSource() == progressTracker) {
            cardLayout.show(screens, "tracker");
        }
        if (evt.getSource() == courses) {
            cardLayout.show(screens, "course");
        }
        if (evt.getSource() == scheduleCT) {
            cardLayout.show(screens, "scheduleCT");
        }
        if (evt.getSource() == logout) {
            cardLayout.show(screens, "welcome");
        }

    }

}

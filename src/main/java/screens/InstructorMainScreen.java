package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    /**
     * The window of the main screen with buttons connecting to each use case
     */
    public InstructorMainScreen(JPanel screens, CardLayout cardLayout) {

        this.cardLayout = cardLayout;
        this.screens = screens;

        // Create label for title of screen
        JLabel title = new JLabel("32 Things To Do");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create buttons
        taskCreate = new JButton("New Task");
        calendar = new JButton("Calendar");
        courses = new JButton("Courses");
//        scheduleCT = new JButton("Schedule Collaborative Task");
        logout = new JButton("Logout");

        taskCreate.addActionListener(this);
        calendar.addActionListener(this);
        courses.addActionListener(this);
//        scheduleCT.addActionListener(this);
        logout.addActionListener(this);

        // Create panel for buttons
        JPanel buttons = new JPanel();
        buttons.add(taskCreate);
        buttons.add(calendar);
        buttons.add(courses);
//        buttons.add(scheduleCT);
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
            cardLayout.show(screens, "toDoList");
        }
        if (evt.getSource() == calendar) {
            cardLayout.show(screens, "calendar");
        }

        if (evt.getSource() == courses) {
            cardLayout.show(screens, "course");
        }
//        if (evt.getSource() == scheduleCT) {
//            cardLayout.show(screens, "scheduleCT");
//        }
        if (evt.getSource() == logout) {
            cardLayout.show(screens, "welcome");
        }

    }
}

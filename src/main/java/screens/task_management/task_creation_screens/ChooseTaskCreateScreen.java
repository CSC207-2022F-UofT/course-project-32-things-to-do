package screens.task_management.task_creation_screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseTaskCreateScreen extends JPanel implements ActionListener {
    // selectable buttons on screen
    JButton event = new JButton("New event");
    JButton assignment = new JButton("New assignment");
    JButton test = new JButton("New test");
    JButton cancel = new JButton("Cancel");

    // for connecting to other screens
    CardLayout cardLayout;
    JPanel screens;

    /**
     * the window for choosing which type of Task to create, after selecting "New task"
     */
    public ChooseTaskCreateScreen(JPanel screens, CardLayout cardLayout) {
        this.cardLayout = cardLayout;
        this.screens = screens;

        // Create label for title of screen
        JLabel title = new JLabel("Select a task type");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add action listeners to buttons
        event.addActionListener(this);
        assignment.addActionListener(this);
        test.addActionListener(this);
        cancel.addActionListener(this);

        // Create panel for buttons
        JPanel buttons = new JPanel();
        buttons.add(event);
        buttons.add(assignment);
        buttons.add(test);
        buttons.add(cancel);

        // Add all components to the panel
        this.add(title);
        this.add(buttons);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Redirect user to Task creation screen corresponding to button click
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New event")) {
            cardLayout.show(screens, "event");
        } else if (e.getActionCommand().equals("New assignment")) {
            cardLayout.show(screens, "assignment");
        } else if (e.getActionCommand().equals("New test")) {
            cardLayout.show(screens, "test");
        } else {
            cardLayout.show(screens, "main");
        }
    }
}

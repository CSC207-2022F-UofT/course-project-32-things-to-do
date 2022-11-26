package scheduling_ct_screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The View for the Scheduling Collaborative Tasks Use Case
 */

public class ScheduleCTScreen extends JPanel implements ActionListener {

    /**
     * The controller
     */
    ScheduleCTController scheduleCTController;
    /**
     * The task title
     */
    JTextField taskTitle = new JTextField(15);
    /**
     * The end time
     */
    JTextField startTime = new JTextField(15);
    /**
     * The start time
     */
    JTextField endTime = new JTextField(15);

    /**
     * Objects for connecting to the other screens
     */
    CardLayout screenLayout;
    JPanel screens;


    public ScheduleCTScreen(ScheduleCTController scheduleCTController, JPanel screens, CardLayout screenLayout) {

        this.scheduleCTController = scheduleCTController;
        this.screenLayout = screenLayout;
        this.screens = screens;


        JLabel title = new JLabel("Scheduling Collaborative Tasks");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskInfo = new LabelTextPanel(new JLabel("Enter task title"), taskTitle);

        LabelTextPanel startInfo = new LabelTextPanel(new JLabel("Enter start time"), startTime);

        LabelTextPanel endInfo = new LabelTextPanel(new JLabel("Enter end time"), endTime);

        JButton cancel = new JButton("Cancel");
        JButton submit = new JButton("Schedule");

        JPanel buttons = new JPanel();
        buttons.add(cancel);
        buttons.add(submit);

        cancel.addActionListener(this);
        submit.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(taskInfo);
        this.add(startInfo);
        this.add(endInfo);
        this.add(buttons);
    }

    // React to button click that results in evt
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Cancel")) {
            screenLayout.show(screens, "main");
        } else if (evt.getActionCommand().equals("Schedule")) {
            try {
                scheduleCTController.isConflict(taskTitle.getText(), startTime.getText(), endTime.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }


        }
    }
}

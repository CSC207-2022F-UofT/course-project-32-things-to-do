package scheduling_ct_screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The View for the Scheduling Collaborative Tasks Use Case
 */

public class EnterTimeAndTask extends JFrame implements ActionListener {

    ScheduleCTController scheduleCTController;
    JTextField taskTitle = new JTextField(15);
    JTextField startTime = new JTextField(15);
    JTextField endTime = new JTextField(15);

    /**
     * Prepares screen that the user inputs information (task title, start time, end time) into
     * @param scheduleCTController - the instance of the ScheduleCTController
     */
    public EnterTimeAndTask(ScheduleCTController scheduleCTController) {

        this.scheduleCTController = scheduleCTController;


        JLabel title = new JLabel("Scheduling Collaborative Tasks");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskInfo = new LabelTextPanel(new JLabel("Enter task title"), taskTitle);

        LabelTextPanel startInfo = new LabelTextPanel(new JLabel("Enter start time as yyyy-MM-dd HH:mm"), startTime);

        LabelTextPanel endInfo = new LabelTextPanel(new JLabel("Enter end time as yyyy-MM-dd HH:mm"), endTime);


        JButton submit = new JButton("Submit");

        JPanel buttons = new JPanel();
        buttons.add(submit);

        submit.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(taskInfo);
        main.add(startInfo);
        main.add(endInfo);
        main.add(buttons);
        this.setContentPane(main);

        this.pack();
        setVisible(true);
    }

    /**
     * Reacts to the (submit) button click that results in event, passing information to the scheduleCTController
     * @param evt the event to be processed
     */
    public void actionPerformed(ActionEvent evt) {
        scheduleCTController.isConflict(taskTitle.getText(), startTime.getText(), endTime.getText());
    }
}

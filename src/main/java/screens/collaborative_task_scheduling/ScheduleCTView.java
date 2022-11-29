package screens.collaborative_task_scheduling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScheduleCTView extends JPanel implements ScheduleCTViewInterface, ActionListener {

    /**
     * Objects for connecting to the other screens
     */
    CardLayout screenLayout;
    JPanel screens;

    /**
     * Constructor for PresentOutput
     * @param screenLayout - the connecting CardLayout screen
     * @param screens - the connecting JPanel screens
     */
    public ScheduleCTView(CardLayout screenLayout, JPanel screens) {
        this.screenLayout = screenLayout;
        this.screens = screens;
    }

    /**
     * Method implemented from PresentOutputInterface which presents the dates the user wishes to schedule
     * @param scheduleCTFormatter - a ScheduleCTFormatter which stores the dates to present
     */
    @Override
    public void present(ScheduleCTFormatter scheduleCTFormatter) {
        ArrayList<String> dateTimes = scheduleCTFormatter.getScheduledDateTimes();

        JLabel title = new JLabel("Scheduled Times");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel labels = new JPanel();

        for (String timeBlock : dateTimes) {
            JLabel time = new JLabel(timeBlock);
            labels.add(time);
        }

        JPanel separateNote = new JPanel();
        JLabel note = new JLabel("Remember to communicate these times to your teammates!");
        separateNote.add(note);


        JButton returnToMain = new JButton("Return to main");
        JPanel buttons = new JPanel();
        buttons.add(returnToMain);

        returnToMain.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(labels);
        this.add(separateNote);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        try {
            screenLayout.show(screens, "main");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}

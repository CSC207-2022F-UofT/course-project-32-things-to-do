package screens.collaborative_task_scheduling;

import use_cases.collaborative_task_scheduling.scheduling_ct_use_case.*;

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
     * @param responseModel - a ScheduleCTResponseModel which stores the dates to present
     */
    @Override
    public void present(ScheduleCTResponseModel responseModel) {
        ArrayList<String> dateTimes = responseModel.getScheduledTimes();

        JLabel title = new JLabel("Scheduled Times");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);

        for (String timeBlock : dateTimes) {
            JPanel timeLabel = new JPanel();
            JLabel time = new JLabel(timeBlock);
            timeLabel.add(time);
            this.add(timeLabel);
        }

        JPanel separateNote = new JPanel();
        JLabel note = new JLabel("Remember to communicate these times to your teammates!");
        separateNote.add(note);


        JButton returnToMain = new JButton("Return to main");
        JPanel buttons = new JPanel();
        buttons.add(returnToMain);

        returnToMain.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(separateNote);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        try {
            screenLayout.show(screens, "StudentMain");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
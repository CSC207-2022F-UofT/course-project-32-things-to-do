package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AvailableTimesScreen extends JFrame implements CollaborativeSchedulingPresenterInterface,
        ActionListener{

    ScheduleTaskController scheduleTaskController;

    CollaborativeSchedulingPresenter collaborativeSchedulingPresenter;

    // list of times screen with pressable buttons
    //      when you press the button, it darkens so that it indicates that it will be scheduled

    public ArrayList<String> present(CollaborativeSchedulingPresenter output) {
        return output.convertToString();
    }

    JTextField task = new JTextField(15);

    JTextField timesToSchedule = new JTextField(20);
    JButton scheduleTime = new JButton("Schedule");


    public AvailableTimesScreen(ScheduleTaskController scheduleTaskController,
                                CollaborativeSchedulingPresenter collaborativeSchedulingPresenter) {

        this.scheduleTaskController = scheduleTaskController;
        this.collaborativeSchedulingPresenter = collaborativeSchedulingPresenter;

        ArrayList<String> presentThese = present(collaborativeSchedulingPresenter);

        JLabel title = new JLabel("Available Times");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel enterTask = new JLabel("Enter CollaborativeTask");
        enterTask.setBounds(10, 50, 300, 20);

        JLabel chooseTime = new JLabel("Enter the times you want to schedule in the format provided" +
                "and separated by a comma " +
                "(e.g. 2022-03-14 2:00,2022-11-15");
        JPanel availableTimes = new JPanel();

        for (String time : presentThese) {
            JButton possibleTime = new JButton(time);
            availableTimes.add(possibleTime);
        }

        scheduleTime.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(enterTask);
        this.add(task);
        this.add(availableTimes);
        this.add(chooseTime);
        this.add(timesToSchedule);
        this.add(scheduleTime);
    }
    // React to a button click that results in evt
    public void actionPerformed(ActionEvent evt) {
        ArrayList<String> selectedTimes = new ArrayList<>();
        if (evt.getSource() == scheduleTime) {
            JOptionPane.showMessageDialog(this, "Successfully inputted into calendar");
        }
        scheduleTaskController.addTimes(task.getText(), timesToSchedule.getText());
    }





}

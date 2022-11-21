package screens;

import use_case_collaborative_scheduling.CollaborativeSchedulingResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AvailableTimesScreen extends JFrame implements CollaborativeSchedulingPresenterInterface,
        ActionListener{

    ScheduleTaskController scheduleTaskController;

    CollaborativeSchedulingPresenter collaborativeSchedulingPresenter;

    public ArrayList<String> present(CollaborativeSchedulingPresenter output) {
        CollaborativeSchedulingResponseModel responseModel = output.getOutputData();
        return output.prepareAvailableTimes(responseModel).getScheduleString();
    }

    JTextField taskName = new JTextField(15);
    JTextField timesToSchedule = new JTextField(20);
    JButton scheduleTime = new JButton("Schedule");
//    JPanel availableTimes = new JPanel();


    public AvailableTimesScreen(ScheduleTaskController scheduleTaskController,
                                CollaborativeSchedulingPresenter collaborativeSchedulingPresenter) {

        this.scheduleTaskController = scheduleTaskController;
        this.collaborativeSchedulingPresenter = collaborativeSchedulingPresenter;

        ArrayList<String> presentThese = present(collaborativeSchedulingPresenter);


        JLabel title = new JLabel("Available Times");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskInfo = new LabelTextPanel(new JLabel("Re-enter task name"), taskName);

        LabelTextPanel timeInfo = new LabelTextPanel(new JLabel("Enter schduled times separate by a comma"),
                timesToSchedule);

        JLabel time1 = new JLabel(presentThese.get(0));

        JButton scheduleTime = new JButton("Schedule");

        scheduleTime.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(time1);
        main.add(taskInfo);
        main.add(timeInfo);
        main.add(scheduleTime);
        this.setContentPane(main);

        this.pack();
        setVisible(true);
    }
    // React to a button click that results in evt
    public void actionPerformed(ActionEvent evt) {
        // ArrayList<String> selectedTimes = new ArrayList<>();
//        if (evt.getSource() == scheduleTime) {
//            JOptionPane.showMessageDialog(this, "Successfully inputted into calendar");
//        }
        scheduleTaskController.addTimes(taskName.getText(), timesToSchedule.getText());
    }





}

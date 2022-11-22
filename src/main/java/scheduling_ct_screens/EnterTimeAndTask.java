package scheduling_ct_screens;

import scheduling_ct_use_case.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class EnterTimeAndTask extends JFrame implements ActionListener {

    ScheduleCTController scheduleCTController;
    JTextField taskTitle = new JTextField(15);
    JTextField username = new JTextField(15);
    JTextField startTime = new JTextField(15);
    JTextField endTime = new JTextField(15);


    public EnterTimeAndTask(ScheduleCTController scheduleCTController) {

        this.scheduleCTController = scheduleCTController;


        JLabel title = new JLabel("Scheduling Collaborative Tasks");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskInfo = new LabelTextPanel(new JLabel("Enter task title"), taskTitle);

        LabelTextPanel userInfo = new LabelTextPanel(new JLabel("Enter task title"), taskTitle);

        LabelTextPanel startInfo = new LabelTextPanel(new JLabel("Enter start time"), startTime);

        LabelTextPanel endInfo = new LabelTextPanel(new JLabel("Enter end time"), endTime);


        JButton submit = new JButton("Submit");

        JPanel buttons = new JPanel();
        buttons.add(submit);

        submit.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(taskInfo);
        main.add(userInfo);
        main.add(startInfo);
        main.add(endInfo);
        main.add(buttons);
        this.setContentPane(main);

        this.pack();
        setVisible(true);
    }

    // React to button click that results in evt
    public void actionPerformed(ActionEvent evt) {
//        System.out.println("Click" + evt.getActionCommand());
        scheduleCTController.isConflict(taskTitle.getText(), username.getText(), startTime.getText(), endTime.getText());


    }
}

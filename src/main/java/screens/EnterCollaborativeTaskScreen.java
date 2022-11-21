package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.showMessageDialog;



public class EnterCollaborativeTaskScreen extends JFrame implements ActionListener{

    JTextField taskTitle = new JTextField(15);
    CollaborativeSchedulingController collaborativeSchedulingController;


    public EnterCollaborativeTaskScreen(CollaborativeSchedulingController collaborativeSchedulingController){

        this.collaborativeSchedulingController = collaborativeSchedulingController;

        JLabel title = new JLabel("Scheduling Collaborative Tasks");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskInfo = new LabelTextPanel(new JLabel("Enter task title"), taskTitle);

        JButton submit = new JButton("Submit");

        JPanel buttons = new JPanel();
        buttons.add(submit);

        submit.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(taskInfo);
        main.add(buttons);
        this.setContentPane(main);

        this.pack();
        setVisible(true);
    }

    // React to button click that results in evt
    public void actionPerformed(ActionEvent evt) {
//        System.out.println("Click" + evt.getActionCommand());

        collaborativeSchedulingController.findTimes(taskTitle.getText());
    }



}
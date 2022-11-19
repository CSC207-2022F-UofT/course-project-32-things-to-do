package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.showMessageDialog;



public class EnterCollaborativeTaskScreen extends JFrame implements ActionListener{

    JTextField task = new JTextField(15);
    CollaborativeSchedulingController collaborativeSchedulingController;


    public EnterCollaborativeTaskScreen(CollaborativeSchedulingController collaborativeSchedulingController){

        this.collaborativeSchedulingController = collaborativeSchedulingController;

        JLabel title = new JLabel("Scheduling Collaborative Tasks");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel enterTask = new JLabel("Enter CollaborativeTask");
        enterTask.setBounds(10, 50, 300, 20);

        JButton submit = new JButton("Submit");

        JPanel buttons = new JPanel();
        buttons.add(submit);

        submit.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(enterTask);
        this.add(task);
        this.add(buttons);

        // making the frame
//        JFrame frame = new JFrame();
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 400);
//        frame.setLocation(200, 400);
//
//        frame.setBounds(200, 200, 500, 500);
//
//        Container cont = frame.getContentPane();
//        cont.setLayout(null);
//        JTextField inputTask = new JTextField();
//        cont.add(inputTask);
//        inputTask.setBounds(100, 50, 300, 30);
//        frame.setTitle("Enter title of collaborative task");
//
//
//
//        JButton submitButton = new JButton("Submit");
//
//        submitButton.setBounds(200, 100, 150, 40);
//        submitButton.setFocusable(false);
        // submitButton.addActionListener(this);

//        frame.add(inputTask);
//        cont.add(submitButton);


    }

    // React to button click that results in evt
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click" + evt.getActionCommand());

        collaborativeSchedulingController.findTimes(task.getText());
    }



}
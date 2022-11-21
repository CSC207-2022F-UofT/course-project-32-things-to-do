package screens;

// Framework/Driver layer

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseFoundScreen extends JFrame implements ActionListener {
    /**
     * Window with title and 2 JButtons.
     */
    public CourseFoundScreen() {
        JLabel title = new JLabel("Course Found!");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton cancel = new JButton("Cancel");
        JButton enrol = new JButton("Enrol");

        JPanel buttons = new JPanel();
        buttons.add(cancel);
        buttons.add(enrol);

        cancel.addActionListener(this);
        enrol.addActionListener(this);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(buttons);
        this.setContentPane(main);
        this.pack();
    }

    /**
     * react to button click that results in evt. */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
}

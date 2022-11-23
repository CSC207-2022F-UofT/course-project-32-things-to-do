package scheduling_ct_screens;

import javax.swing.*;
import java.awt.*;

public class PresentOutput extends JFrame {

    String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public PresentOutput() {

        JLabel title = new JLabel("Message screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel text = new JLabel(message);

        JPanel main = new JPanel();
        // main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(text);

        JFrame frame = new JFrame("message screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(main);
        frame.pack();
        frame.setVisible(true);
    }
}

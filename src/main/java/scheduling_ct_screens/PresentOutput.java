package scheduling_ct_screens;

import javax.swing.*;

public class PresentOutput extends JFrame {

    String message;

    public PresentOutput(String message) {
        this.message = message;

        JLabel text = new JLabel(message);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(text);
        setVisible(true);
    }
}

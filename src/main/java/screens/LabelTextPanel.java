package screens;

import javax.swing.*;

/**
 * UI helper class, couples Jlabel with JTextField
 * (in View layer)
 */

public class LabelTextPanel extends JPanel {
    public LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}

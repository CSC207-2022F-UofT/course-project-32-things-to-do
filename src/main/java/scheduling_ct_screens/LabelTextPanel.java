package scheduling_ct_screens;

import javax.swing.*;

// Frameworks/Drivers layer

/**
 * UI helper class that creates labeled text panel
 */

public class LabelTextPanel extends JPanel {
    public LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}
package scheduling_ct_screens;

import javax.swing.*;

// Frameworks/Drivers layer

/**
 * UI helper class that creates labeled text panel
 */

public class LabelTextPanel extends JPanel {

    /**
     * Creates a labeled text panel
     * @param label - the label
     * @param textField - the text field associated with the label
     */
    public LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}
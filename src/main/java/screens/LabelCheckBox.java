package screens;

import javax.swing.*;

public class LabelCheckBox extends JCheckBox {
    public LabelCheckBox(JLabel label, JCheckBox checkBox) {
        this.add(label);
        this.add(checkBox);
    }
}

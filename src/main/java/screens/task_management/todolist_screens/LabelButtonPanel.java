package screens.task_management.todolist_screens;

import javax.swing.*;

/**
 * UI helper class that couples given JLabel and JButton components together in a JPanel
 */

public class LabelButtonPanel extends JPanel{
    public LabelButtonPanel(JLabel label, JButton button) {
        this.add(label);
        this.add(button);
    }
}

package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen extends JPanel implements ActionListener {

    /**
     * Buttons for the screen
     */
    JButton logIn;
    JButton signUp;

    /**
     * Objects for connecting to the other screens
     */
    CardLayout cardLayout;
    JPanel screens;

    /**
     * A window with a title and a JButton.
     */
    public WelcomeScreen(CardLayout cardLayout, JPanel screens) {

        this.cardLayout = cardLayout;
        this.screens = screens;

        JLabel title = new JLabel("Welcome Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        logIn = new JButton("Log in");
        signUp = new JButton("Sign up");

        JPanel buttons = new JPanel();
        buttons.add(logIn);
        buttons.add(signUp);

        logIn.addActionListener(this);
        signUp.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == logIn) {
            cardLayout.show(screens, "login");
        }

        if (evt.getSource() == signUp) {
            cardLayout.show(screens, "register");
        }
    }

}

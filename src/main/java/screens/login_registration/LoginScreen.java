package screens.login_registration;

import screens.LabelTextPanel;
import use_cases.login_registration.login_usecase.LoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class LoginScreen extends JPanel implements ActionListener {
    /**
     * The username chosen by the user
     */
    JTextField username = new JTextField(15);
    /**
     * The password
     */
    JPasswordField password = new JPasswordField(15);

    LoginController loginController;

    CardLayout cardLayout;

    JPanel screens;

    public LoginScreen(LoginController controller, CardLayout cardLayout, JPanel screens) {
        this.loginController = controller;
        this.cardLayout = cardLayout;
        this.screens = screens;

        // Add titles and text panels

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), username);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), password);

        // add login and cancel buttons
        JButton logIn = new JButton("Log in");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(logIn);
        buttons.add(cancel);

        // make the login and cancel buttons do something when clicked
        logIn.addActionListener(this);
        cancel.addActionListener(this);

        // add all the components to the same screen
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in event.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if (evt.getActionCommand().equals("Cancel")) {
            cardLayout.show(screens, "welcome");
        } else {
            try {
                LoginResponseModel l = loginController.create(username.getText(),
                        String.valueOf(password.getPassword()));
                showMessageDialog(this, "Successfully logged in.");
                if (l.getTypeOfUser().equals("Instructor")) {
                    cardLayout.show(screens, "InstructorMain");
                } else {
                    cardLayout.show(screens, "StudentMain");
                }
            } catch (Exception e) {
                showMessageDialog(this, "Login failed: username does not exist or " +
                        "password is incorrect");
            } catch (LoginFailed e) {
                showMessageDialog(this, "Login failed: username does not exist or " +
                        "password is incorrect");
                throw new RuntimeException(e);
            }
        }
    }

}

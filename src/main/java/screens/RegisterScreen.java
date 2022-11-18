package screens;

import user_register_usecase.UserRegRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

// Frameworks/Drivers layer

public class RegisterScreen extends JPanel implements ActionListener {
    /**
     * The username chosen by the user
     */
    JTextField username = new JTextField(15);
    /**
     *  The password
     */
    JPasswordField password = new JPasswordField(15);
    /**
     * The second password to make sure the user understands
     */
    JPasswordField repeatPassword = new JPasswordField(15);

    /**
     * The type of User this user is
     */

    /**
     * The controller
     */
    UserRegController userRegController;

    /**
     * A window with a title and a JButton.
     */
    public RegisterScreen(UserRegController controller) {

        this.userRegController = controller;

        JLabel title = new JLabel("Register Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Choose username"), username);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Choose password"), password);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel("Enter password again"), repeatPassword);
        JTextField typeOfUser;
//        LabelButtonPanel typeOfUserInfo = new LabelTextPanel(
//                new JLabel("Are you an instructor or a student?"), typeOfUser);

        JButton instructorUser = new JButton("Instructor");
        JButton studentUser = new JButton("Student");
        JButton signUp = new JButton("Sign up");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(signUp);
        buttons.add(cancel);
//        buttons.add(typeOfUser);

//        typeOfUser.addActionListener(this);
        signUp.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);

    }

    private void add(LabelTextPanel usernameInfo) {
    }

    /**
     * React to a button click that results in event.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        try {
            userRegController.create(username.getText(),
                    String.valueOf(password.getPassword()),
                    String.valueOf(repeatPassword.getPassword()));
            showMessageDialog(this, "%s created.".format(username.getText()));
        } catch (Exception e) {
            showMessageDialog(this, e.getMessage());
        }
    }
}
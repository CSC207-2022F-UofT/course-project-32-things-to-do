package screens.login_registration;

import screens.LabelTextPanel;

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
    JTextField typeOfUser = new JTextField(10);

    /**
     * The controller
     */
    UserRegController userRegController;

    /**
     * Objects for connecting to the other screens
     */
    CardLayout cardLayout;
    JPanel screens;

    /**
     * A window with a title and a JButton.
     */
    public RegisterScreen(UserRegController controller, CardLayout cardLayout, JPanel screens) {

        this.userRegController = controller;
        this.cardLayout = cardLayout;
        this.screens = screens;

        JLabel title = new JLabel("Register Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

//        JPanel userButtons = new JPanel();
//        userButtons.add(instructor);
//        userButtons.add(student);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Choose username"), username);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Choose password"), password);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel("Enter password again"), repeatPassword);
        LabelTextPanel chooseTypeOfUser = new LabelTextPanel(
                new JLabel("I am a (type 'Instructor' or 'Student')") , typeOfUser);
//        JLabel chooseTypeOfUser = new JLabel("I am a:");
//        userButtons.add(chooseTypeOfUser);

        JButton signUp = new JButton("Sign up");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(signUp);
        buttons.add(cancel);

//        typeOfUser.addActionListener(this);
        signUp.addActionListener(this);
        cancel.addActionListener(this);
//        instructor.addActionListener(this);
//        student.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(chooseTypeOfUser);
        this.add(buttons);
//        this.add(userButtons);

    }

//    private void add(LabelTextPanel usernameInfo) {
//    }

    /**
     * React to a button click that results in event.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        if (evt.getActionCommand().equals("Cancel")) {
            cardLayout.show(screens, "welcome");
        } else {
            try {
                userRegController.create(username.getText(),
                        String.valueOf(password.getPassword()),
                        String.valueOf(repeatPassword.getPassword()),
                        String.valueOf(typeOfUser.getText()));
                showMessageDialog(this, "Registration successful and logged in");
                if (String.valueOf(typeOfUser.getText()).equals("Student")) {
                    cardLayout.show(screens, "StudentMain");
                } else if (String.valueOf(typeOfUser.getText()).equals("Instructor")) {
                    cardLayout.show(screens, "InstructorMain");
                }

            } catch (Exception e) {
                showMessageDialog(this, e.getMessage());
            }
        }
    }
}
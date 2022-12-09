package use_cases.login_registration.user_register_usecase;

import entities.*;

import java.io.Serializable;

// Use Case Layer

public class UserRegSaveRequest implements Serializable {
    /**
     * A transient data storage class that contains the same information as a User
     * The parent to InstructorSaveRequest and StudentSaveRequest
     */

    private final String name;

    private final String password;

    /**
     * @param name the name of this User
     * @param password the password of this User
     */
    public UserRegSaveRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() { return name; }

    public String getPass() { return password; }


    /**
     * @return a User based on the information stored in this UserRegSaveRequest object
     * Default is StudentUser
     */
    public User initializeUser() {
        return new StudentUser(this.name, this.password);
    }

}

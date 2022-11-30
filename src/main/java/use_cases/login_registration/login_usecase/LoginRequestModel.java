package use_cases.login_registration.login_usecase;

public class LoginRequestModel {

    /**
     * A login request that contains the user's input for name and password
     */
    private String name;

    private String password;

    /**
     * @param name the entered username
     * @param password the entered password
     */
    public LoginRequestModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    String getName() { return name; }

    void setName(String name) { this.name = name; }

    String getPass() { return password; }

    void setPass(String pass) {
        this.password = pass;
    }

}

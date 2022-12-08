package use_cases.login_registration.login_usecase;

public class LoginRequestModel {

    /**
     * A login request that contains the user's input for name and password
     */
    private final String name;

    private final String password;

    /**
     * @param name the entered username
     * @param password the entered password
     */
    public LoginRequestModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() { return name; }

    public String getPass() { return password; }

}

package use_cases.login_registration.user_register_usecase;

public class UserRegResponse {
    /**
     * A response to the request--contains the user's name and the creation time.
     */
    String login; // the username

    String creationTime;

    /**
     * @param login the name of the user that has just logged in
     * @param creationTime the time of login
     */
    public UserRegResponse(String login, String creationTime) {
        this.login = login;
        this.creationTime = creationTime;
    }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }


}

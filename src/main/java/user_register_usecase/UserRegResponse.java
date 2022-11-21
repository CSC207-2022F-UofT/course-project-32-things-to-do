package user_register_usecase;

public class UserRegResponse {
    /**
     * A response to the request--contains the user's name and the creation time.
     */
    String login; // the username

    String creationTime;

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
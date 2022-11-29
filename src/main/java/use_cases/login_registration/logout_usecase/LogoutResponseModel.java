package use_cases.login_registration.logout_usecase;

public class LogoutResponseModel {

    /**
     * A response to the user's request to logout
     */
    String name;

    String logoutTime;

    /**
     * @param name the username
     * @param timeOfLogout the time of logout
     */
    public LogoutResponseModel(String name, String timeOfLogout) {
        this.name = name;
        this.logoutTime = timeOfLogout;
    }

    public String getName() { return name; }

    public void setName(String username) { this.name = username; }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String TimeOfLogout) {
        this.logoutTime = TimeOfLogout;
    }

}

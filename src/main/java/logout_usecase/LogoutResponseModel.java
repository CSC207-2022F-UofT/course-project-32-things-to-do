package logout_usecase;

public class LogoutResponseModel {

    String name;

    String logoutTime;
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

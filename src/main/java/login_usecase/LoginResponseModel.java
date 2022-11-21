package login_usecase;

public class LoginResponseModel {

    String name;

    String loginTime;

    public LoginResponseModel(String username, String timeOfLogin) {
        this.name = username;
        this.loginTime = timeOfLogin;
    }

    public String getName() { return name; }

    public void setName(String username) { this.name = username; }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String TimeOfLogin) {
        this.loginTime = TimeOfLogin;
    }

}

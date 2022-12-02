package use_cases.login_registration.login_usecase;

public class LoginResponseModel {

    /**
     * A login response model with the username and the time of login
     */
    String name;

    String loginTime;

    String typeOfUser;

    /**
     * @param username the name of the User that just logged in
     * @param timeOfLogin the time at which this User logged in
     */
    public LoginResponseModel(String username, String timeOfLogin, String typeOfUser) {
        this.name = username;
        this.loginTime = timeOfLogin;
        this.typeOfUser = typeOfUser;
    }

    public String getName() { return name; }

    public void setName(String username) { this.name = username; }

    public String getTypeOfUser() { return typeOfUser; }

    public void setTypeOfUser(String s) {
        if (s.equals("Instructor") | s.equals("Student")) {
            this.typeOfUser = s;
        }
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String TimeOfLogin) {
        this.loginTime = TimeOfLogin;
    }

}

package login_usecase;

public class LoginRequestModel {

    private String name;

    private String password;

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

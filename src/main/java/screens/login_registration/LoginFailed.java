package screens.login_registration;

public class LoginFailed extends Throwable {
    public LoginFailed(String error) {
        super(error);
    }
}

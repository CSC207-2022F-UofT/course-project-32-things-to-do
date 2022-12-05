package screens.login_registration;

public class LogoutFailed extends Throwable {

    public LogoutFailed(String error) {
        super(error);
    }
}
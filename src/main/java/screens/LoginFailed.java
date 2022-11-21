package screens;

public class LoginFailed extends Throwable {
    public LoginFailed(String error) {
        super(error);
    }
}

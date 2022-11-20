package login_usecase;

import screens.LoginFailed;

public interface LoginPresenter {

    LoginResponseModel prepareSuccessView(LoginResponseModel login);

    LoginResponseModel prepareFailView(String error) throws LoginFailed;

}

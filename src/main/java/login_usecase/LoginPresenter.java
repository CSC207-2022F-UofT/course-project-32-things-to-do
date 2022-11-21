package login_usecase;

import screens.LoginFailed;

public interface LoginPresenter {

    /**
     * @param login the login response model
     * @return a successful or failure login response model
     */
    LoginResponseModel prepareSuccessView(LoginResponseModel login);

    LoginResponseModel prepareFailView(String error) throws LoginFailed;

}

package use_cases.login_registration.login_usecase;

import screens.login_registration.LoginFailed;

public interface LoginPresenter {

    /**
     * @param login the login response model
     * @return a successful or failure login response model
     */
    LoginResponseModel prepareSuccessView(LoginResponseModel login);

    LoginResponseModel prepareFailView(String error) throws LoginFailed;

}

package login_usecase;

import screens.LoginFailed;

public interface LoginInputBoundary {

    LoginResponseModel create(LoginRequestModel request) throws LoginFailed;
}

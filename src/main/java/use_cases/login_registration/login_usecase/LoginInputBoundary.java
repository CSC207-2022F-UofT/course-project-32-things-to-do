package use_cases.login_registration.login_usecase;

import screens.login_registration.*;

public interface LoginInputBoundary {

    /**
     * @param request the Login request (containing the user's input)
     * @return the response to the login request
     * @throws LoginFailed if the username doesn't exist or the password is incorrect
     */
    LoginResponseModel create(LoginRequestModel request) throws LoginFailed;
}

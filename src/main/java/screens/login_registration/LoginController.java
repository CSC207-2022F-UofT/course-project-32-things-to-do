package screens.login_registration;

import use_cases.login_registration.login_usecase.LoginInputBoundary;
import use_cases.login_registration.login_usecase.LoginRequestModel;
import use_cases.login_registration.login_usecase.LoginResponseModel;

// Interface adapters layer

public class LoginController {

    final LoginInputBoundary userInput;

    public LoginController(LoginInputBoundary accGateway) {
        this.userInput = accGateway;
    }

    LoginResponseModel create(String name, String password) throws LoginFailed {
        LoginRequestModel request = new LoginRequestModel(name, password);

        return userInput.create(request);
    }

}

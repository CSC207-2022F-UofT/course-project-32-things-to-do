package screens;

import login_usecase.LoginInputBoundary;
import login_usecase.LoginRequestModel;
import login_usecase.LoginResponseModel;

// Interface adapters layer

public class LoginController {

    final LoginInputBoundary userInput;

    public LoginController(LoginInputBoundary accGateway) {
        this.userInput = accGateway;
    }

    LoginResponseModel create(String name, String password) {
        LoginRequestModel request = new LoginRequestModel(name, password);

        return userInput.create(request);
    }

}

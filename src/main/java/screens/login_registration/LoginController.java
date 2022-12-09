package screens.login_registration;

import use_cases.login_registration.login_usecase.*;

// Interface adapters layer

public class LoginController {

    final LoginInputBoundary userInput;

    public LoginController(LoginInputBoundary accGateway) {
        this.userInput = accGateway;
    }

    /**
     * @param name the name entered by the user
     * @param password the password entered by the user
     * @return a response to this request to log in
     * @throws LoginFailed if the username doesn't exist or the password is incorrect
     */
    LoginResponseModel create(String name, String password) throws LoginFailed {
        LoginRequestModel request = new LoginRequestModel(name, password);

        return userInput.create(request);
    }

}

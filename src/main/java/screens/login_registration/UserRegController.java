package screens.login_registration;

import use_cases.login_registration.user_register_usecase.UserRegInputBoundary;
import use_cases.login_registration.user_register_usecase.UserRegRequest;
import use_cases.login_registration.user_register_usecase.UserRegResponse;

import java.io.IOException;

// Interface Adapters Layer

public class UserRegController {

    final UserRegInputBoundary userInput;

    public UserRegController(UserRegInputBoundary accGateway) {
        this.userInput = accGateway;
    }

    // create a request to register the user
    UserRegResponse create(String name, String pass1, String pass2, String typeOfUser) throws IOException {
        UserRegRequest request = new UserRegRequest(name, pass1, pass2, typeOfUser);
        return userInput.create(request);
    }
}

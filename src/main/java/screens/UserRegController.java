package screens;

import user_register_usecase.UserRegInputBoundary;
import user_register_usecase.UserRegRequest;
import user_register_usecase.UserRegResponse;

// Interface Adapters Layer

public class UserRegController {

    final UserRegInputBoundary userInput;

    public UserRegController(UserRegInputBoundary accGateway) {
        this.userInput = accGateway;
    }

    UserRegResponse create(String name, String pass1, String pass2) {
        UserRegRequest request = new UserRegRequest(name, pass1, pass2);
        return userInput.create(request);
    }
}

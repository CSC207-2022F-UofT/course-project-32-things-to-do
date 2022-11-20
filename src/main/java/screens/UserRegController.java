package screens;

import user_register_usecase.UserRegInputBoundary;
import user_register_usecase.UserRegRequest;
import user_register_usecase.UserRegResponse;

import java.io.IOException;

// Interface Adapters Layer

public class UserRegController {

    final UserRegInputBoundary userInput;

    public UserRegController(UserRegInputBoundary accGateway) {
        this.userInput = accGateway;
    }

    UserRegResponse create(String name, String pass1, String pass2, String typeOfUser) throws IOException {
        UserRegRequest request = new UserRegRequest(name, pass1, pass2, typeOfUser);
        return userInput.create(request);
    }
}

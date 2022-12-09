package screens.login_registration;

import use_cases.login_registration.user_register_usecase.*;

import java.io.IOException;

// Interface Adapters Layer

public class UserRegController {

    final UserRegInputBoundary userInput;

    public UserRegController(UserRegInputBoundary accGateway) {
        this.userInput = accGateway;
    }

    // create a request to register the user
    void create(String name, String pass1, String pass2, String typeOfUser) throws IOException {
        UserRegRequest request = new UserRegRequest(name, pass1, pass2, typeOfUser);
        userInput.create(request);
    }
}

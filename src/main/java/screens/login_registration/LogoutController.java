package screens.login_registration;

import use_cases.login_registration.logout_usecase.LogoutInputBoundary;

import java.io.IOException;

public class LogoutController {

    final LogoutInputBoundary interactor;

    public LogoutController(LogoutInputBoundary accGateway) {
        this.interactor = accGateway;
    }

    // the method to logout
    public void create() throws IOException {
        interactor.create();
    }
}

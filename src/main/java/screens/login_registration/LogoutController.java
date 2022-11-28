package screens.login_registration;

import use_cases.login_registration.logout_usecase.LogoutInputBoundary;
import use_cases.login_registration.logout_usecase.LogoutRequestModel;
import use_cases.login_registration.logout_usecase.LogoutResponseModel;

import java.io.IOException;

public class LogoutController {

    final LogoutInputBoundary userInput;

    public LogoutController(LogoutInputBoundary accGateway) {
        this.userInput = accGateway;
    }

    LogoutResponseModel create(String name, String timeOfLogout) throws IOException {
        LogoutRequestModel request = new LogoutRequestModel();

        return userInput.create(request);
    }
}

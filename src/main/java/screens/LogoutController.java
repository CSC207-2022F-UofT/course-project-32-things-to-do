package screens;

import logout_usecase.LogoutInputBoundary;
import logout_usecase.LogoutRequestModel;
import logout_usecase.LogoutResponseModel;

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

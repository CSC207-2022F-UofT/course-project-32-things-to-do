package use_cases.login_registration.logout_usecase;

import java.io.IOException;

public interface LogoutInputBoundary {

    /**
     * @param request the request to logout
     * @return the logout response
     * @throws IOException
     */
    LogoutResponseModel create(LogoutRequestModel request) throws IOException;
}

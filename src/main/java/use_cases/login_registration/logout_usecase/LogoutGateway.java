package use_cases.login_registration.logout_usecase;

import use_cases.login_registration.user_register_usecase.UserRegSaveRequest;

import java.io.IOException;
import java.util.Map;

public interface LogoutGateway {

    /**
     * @param requestModel the request to logout
     * @throws IOException if something goes wrong while logging out
     */
    void save(UserRegSaveRequest requestModel) throws IOException;

    /**
     * @return The map of usernames to UserRegSaveRequest objects
     */
    Map<String, UserRegSaveRequest> getAccounts();

}

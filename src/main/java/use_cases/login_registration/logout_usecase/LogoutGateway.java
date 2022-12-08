package use_cases.login_registration.logout_usecase;

import use_cases.login_registration.user_register_usecase.*;

import java.io.IOException;

public interface LogoutGateway {

    /**
     * @param requestModel the request to logout
     * @throws IOException if something goes wrong while logging out
     */
    void save(UserRegSaveRequest requestModel) throws IOException;

}

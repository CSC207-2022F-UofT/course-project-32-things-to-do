package logout_usecase;

import user_register_usecase.UserRegSaveRequest;

import java.io.IOException;

public interface LogoutGateway {

    /**
     * @param requestModel the request to logout
     * @throws IOException if something goes wrong while logging out
     */
    void save(UserRegSaveRequest requestModel) throws IOException;

}

package use_cases.login_registration.user_register_usecase;

// Use Case Layer

import java.io.IOException;

public interface UserRegInputBoundary {

    /**
     * @param request the request to register this user
     * @return the response to this request to register the user
     * @throws IOException
     */
    UserRegResponse create(UserRegRequest request) throws IOException;

}

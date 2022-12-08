package use_cases.login_registration.user_register_usecase;

// Use case layer

import java.io.IOException;
import java.util.Map;

public interface UserRegGateway {
    /**
     * The gateway that interacts with the User Database
     * @param identifier the username to look for
     * @return whether there exists in the User Database a user with username identifier
     */

    boolean existsByName(String identifier);

    void save(UserRegSaveRequest requestModel) throws IOException;

}

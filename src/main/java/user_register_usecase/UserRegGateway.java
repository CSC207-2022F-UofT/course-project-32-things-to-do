package user_register_usecase;

// Use case layer

import java.io.IOException;

public interface UserRegGateway {

    boolean existsByName(String identifier);

    void save(UserRegSaveRequest requestModel) throws IOException;
}

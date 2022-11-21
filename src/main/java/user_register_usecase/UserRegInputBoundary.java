package user_register_usecase;

// Use Case Layer

import java.io.IOException;

public interface UserRegInputBoundary {

    UserRegResponse create(UserRegRequest request) throws IOException;

}

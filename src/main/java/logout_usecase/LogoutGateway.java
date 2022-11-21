package logout_usecase;

import user_register_usecase.UserRegSaveRequest;

import java.io.IOException;

public interface LogoutGateway {
    void save(UserRegSaveRequest requestModel) throws IOException;

}

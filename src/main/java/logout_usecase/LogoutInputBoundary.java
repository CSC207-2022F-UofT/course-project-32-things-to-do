package logout_usecase;

import java.io.IOException;

public interface LogoutInputBoundary {

    LogoutResponseModel create(LogoutRequestModel request) throws IOException;
}

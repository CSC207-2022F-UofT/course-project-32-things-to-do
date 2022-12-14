package screens.login_registration;

import use_cases.login_registration.login_usecase.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginResponseFormatter implements LoginPresenter {
    /**
     * @param response the login response model
     * @return a successful login response or a failed login response
     */
    @Override
    public LoginResponseModel prepareSuccessView(LoginResponseModel response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getLoginTime());
        response.setLoginTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        return response;
    }

    @Override
    public LoginResponseModel prepareFailView(String error) throws LoginFailed {
        throw new LoginFailed(error);
    }

}

package screens.login_registration;

import use_cases.login_registration.login_usecase.LoginPresenter;
import use_cases.login_registration.login_usecase.LoginResponseModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginResponseFormatter implements LoginPresenter {
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

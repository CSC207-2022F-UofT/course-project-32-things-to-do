package screens.login_registration;

import use_cases.login_registration.user_register_usecase.UserRegPresenter;
import use_cases.login_registration.user_register_usecase.UserRegResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Interface Adapters Layer

public class UserRegResponseFormatter implements UserRegPresenter {

    @Override
    public UserRegResponse prepareSuccessView(UserRegResponse response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        return response;
    }

    @Override
    public UserRegResponse prepareFailView(String error) {
        throw new UserCreationFailed(error);
    }
}

package screens.login_registration;

import use_cases.login_registration.user_register_usecase.UserRegPresenter;
import use_cases.login_registration.user_register_usecase.UserRegResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Interface Adapters Layer

public class UserRegResponseFormatter implements UserRegPresenter {

    // if user creation was successful
    @Override
    public UserRegResponse prepareSuccessView(UserRegResponse response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        return response;
    }

    // if the user already exists or the password is not valid
    @Override
    public UserRegResponse prepareFailView(String error) {
        throw new UserCreationFailed(error);
    }
}

package screens.login_registration;

import use_cases.login_registration.logout_usecase.LogoutPresenter;
import use_cases.login_registration.logout_usecase.LogoutResponseModel;

public class LogoutResponseFormatter implements LogoutPresenter {

    /**
     * @param logout the logout response
     * @return the successful logout view
     */
    @Override
    public LogoutResponseModel prepareSuccessView(LogoutResponseModel logout) {
        return logout;
    }

}

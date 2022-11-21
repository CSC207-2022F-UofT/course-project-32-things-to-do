package logout_usecase;

public interface LogoutPresenter {

    /**
     * @param logout the logout response
     * @return the successful logout view
     */
    LogoutResponseModel prepareSuccessView(LogoutResponseModel logout);

}

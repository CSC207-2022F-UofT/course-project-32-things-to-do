package use_cases.login_registration.user_register_usecase;

// Use Case Layer

public interface UserRegPresenter {

    /** The presenter for the user register use case--presents a success or fail view
     * @param user the user register response (to the request to register)
     * @return a user register response
     */

    UserRegResponse prepareSuccessView(UserRegResponse user);

    UserRegResponse prepareFailView(String error);
}

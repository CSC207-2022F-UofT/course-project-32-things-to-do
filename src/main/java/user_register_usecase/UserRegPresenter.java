package user_register_usecase;

// Use Case Layer

public interface UserRegPresenter {

    UserRegResponse prepareSuccessView(UserRegResponse user);

    UserRegResponse prepareFailView(String error);
}

package login_usecase;

public interface LoginPresenter {

    LoginResponseModel prepareSuccessView(LoginResponseModel login);

    LoginResponseModel prepareFailView(String error);

}

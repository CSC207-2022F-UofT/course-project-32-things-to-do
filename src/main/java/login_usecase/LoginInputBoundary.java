package login_usecase;

public interface LoginInputBoundary {

    LoginResponseModel create(LoginRequestModel request);
}

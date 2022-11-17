package login_usecase;

import java.time.LocalDateTime;

public class LoginInteractor implements LoginInputBoundary {

    final LoginGateway loginGateway;

    final LoginPresenter loginPresenter;

    public LoginInteractor(LoginGateway loginGate, LoginPresenter loginPres) {
        this.loginGateway = loginGate;
        this.loginPresenter = loginPres;
    }

    @Override
    public LoginResponseModel create(LoginRequestModel requestModel) {
        if (!loginGateway.existsByName(requestModel.getName())) {
            return loginPresenter.prepareFailView("Username does not exist");
        } else if (!loginGateway.passOf(requestModel.getName()).equals(requestModel.getPass())) {
            return loginPresenter.prepareFailView("Incorrect password");
        }

        LocalDateTime now = LocalDateTime.now();
        LoginResponseModel loginRes = new LoginResponseModel(requestModel.getName(), now.toString());
        return loginPresenter.prepareSuccessView(loginRes);

    }
}

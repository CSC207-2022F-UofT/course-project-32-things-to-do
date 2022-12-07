package use_cases.login_registration.login_usecase;

import entities.CurrentUser;
import entities.InstructorUser;
import entities.User;
import screens.login_registration.LoginFailed;
import use_cases.login_registration.user_register_usecase.UserRegSaveRequest;


import java.time.LocalDateTime;

public class LoginInteractor implements LoginInputBoundary {

    final LoginGateway loginGateway;

    final LoginPresenter loginPresenter;

    private User user;

    /**
     * @param loginGate the Gateway class which interacts with the User Database
     * @param loginPres the Presenter class that shows the User the result of their request
     */

    public LoginInteractor(LoginGateway loginGate, LoginPresenter loginPres) {
        this.loginGateway = loginGate;
        this.loginPresenter = loginPres;
    }

    public User getUser() { return this.user; }

    /**
     *
     * @param requestModel the Login request (containing the user's input)
     * @return the response to this login request (whether it worked or not)
     * @throws LoginFailed if the username doesn't exist or the password is incorrect
     */
    @Override
    public LoginResponseModel create(LoginRequestModel requestModel) throws LoginFailed {

        // check if the username exists and if the associated password is correct
        if (!loginGateway.existsByName(requestModel.getName())) {
            return loginPresenter.prepareFailView("Username does not exist");
        } else if (!loginGateway.passOf(requestModel.getName()).equals(requestModel.getPass())) {
            return loginPresenter.prepareFailView("Incorrect password");
        }

        LocalDateTime now = LocalDateTime.now();

        this.user = createUser(requestModel);

        //set the program's current user
        CurrentUser.setCurrentUser(user);

        LoginResponseModel loginRes;
        // create a new response based on the information provided in the request
        if (this.user instanceof InstructorUser) {
            loginRes = new LoginResponseModel(requestModel.getName(), now.toString(), "Instructor");
        } else {
            loginRes = new LoginResponseModel(requestModel.getName(), now.toString(), "Student");
        }
        return loginPresenter.prepareSuccessView(loginRes);
    }

    // create a user based on the login request model (search the database and initialize a new User with
    // the same information stored)
    private User createUser(LoginRequestModel requestModel) {

        // creating a new User object using the information in the UserRegSaveRequest
        UserRegSaveRequest userSave = loginGateway.getAccounts().get(requestModel.getName());

        return userSave.initializeUser();
    }
    }

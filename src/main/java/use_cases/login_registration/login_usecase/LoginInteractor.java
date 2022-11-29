package use_cases.login_registration.login_usecase;

import entities.InstructorUser;
import entities.StudentUser;
import entities.User;
import screens.login_registration.LoginFailed;
import use_cases.login_registration.user_register_usecase.InstructorSaveRequest;
import use_cases.login_registration.user_register_usecase.StudentSaveRequest;
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
     * @throws LoginFailed
     */
    @Override
    public LoginResponseModel create(LoginRequestModel requestModel) throws LoginFailed {
        if (!loginGateway.existsByName(requestModel.getName())) {
            return loginPresenter.prepareFailView("Username does not exist");
        } else if (!loginGateway.passOf(requestModel.getName()).equals(requestModel.getPass())) {
            return loginPresenter.prepareFailView("Incorrect password");
        }

        LocalDateTime now = LocalDateTime.now();
        LoginResponseModel loginRes = new LoginResponseModel(requestModel.getName(), now.toString());

        this.user = createUser(requestModel);
        return loginPresenter.prepareSuccessView(loginRes);
    }

    private User createUser(LoginRequestModel requestModel) {

        // creating a new User object using the information in the UserRegSaveRequest
        UserRegSaveRequest userSave = loginGateway.getAccounts().get(requestModel.getName());

        User user;
        if (userSave instanceof InstructorSaveRequest) {
            user = new InstructorUser(userSave.getName(), userSave.getPass());
            ((InstructorUser) user).setCourses(((InstructorSaveRequest) userSave).getCourses());
        } else {
            user = new StudentUser(userSave.getName(), userSave.getPass());
            ((StudentUser) user).setCourses(((StudentSaveRequest) userSave).getCourses());
            ((StudentUser) user).setToDoList(((StudentSaveRequest) userSave).getCourses());
            ((StudentUser) user).setTaskArchive(((StudentSaveRequest) userSave).getTaskArchive());
            ((StudentUser) user).setInbox(((StudentSaveRequest) userSave).getInbox());
            ((StudentUser) user).setNotifications(((StudentSaveRequest) userSave).getNotifications());
            ((StudentUser) user).setDesiredGrades(((StudentSaveRequest) userSave).getDesiredGrades());
            ((StudentUser) user).setWorkingHours(((StudentSaveRequest) userSave).getWorkingHours());
        }
        return user;
    }
    }

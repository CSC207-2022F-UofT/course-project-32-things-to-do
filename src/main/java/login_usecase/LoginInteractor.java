package login_usecase;

import entities.InstructorUser;
import entities.StudentUser;
import entities.User;
import screens.LoginFailed;
import user_register_usecase.InstructorSaveRequest;
import user_register_usecase.StudentSaveRequest;
import user_register_usecase.UserRegSaveRequest;

import java.time.LocalDateTime;
import java.util.HashMap;

public class LoginInteractor implements LoginInputBoundary {

    final LoginGateway loginGateway;

    final LoginPresenter loginPresenter;

    private User user;

    public LoginInteractor(LoginGateway loginGate, LoginPresenter loginPres) {
        this.loginGateway = loginGate;
        this.loginPresenter = loginPres;
    }

    public User getUser() { return this.user; }

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

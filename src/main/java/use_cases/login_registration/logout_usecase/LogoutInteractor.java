package use_cases.login_registration.logout_usecase;

import entities.CurrentUser;
import entities.InstructorUser;
import entities.StudentUser;
import entities.User;
import use_cases.login_registration.user_register_usecase.InstructorSaveRequest;
import use_cases.login_registration.user_register_usecase.StudentSaveRequest;
import use_cases.login_registration.user_register_usecase.UserRegSaveRequest;

import java.io.IOException;
import java.time.LocalDateTime;


public class LogoutInteractor implements LogoutInputBoundary {

    /**
     * The use case for logging out.
     */
    final LogoutGateway userGateway;

    private User user;

    /**
     * @param gateway the logout gateway (which interacts with the User database)
     */
    public LogoutInteractor(LogoutGateway gateway) {
        this.userGateway = gateway;
        this.user = CurrentUser.getCurrentUser();
    }

    /**
     * Save a new UserRegSaveRequest which contains all of the information in the User that is trying to
     * log out into the User database.
     * @throws IOException if logout fails
     */
    @Override
    public void create() throws IOException {
        this.user = CurrentUser.getCurrentUser();
        LocalDateTime now = LocalDateTime.now();

        UserRegSaveRequest userModel;

        if (user instanceof StudentUser) {
            userModel = new StudentSaveRequest(user.getName(), user.getPass(),
                    (StudentUser) user, now);
        } else if (user instanceof InstructorUser) {
            userModel = new InstructorSaveRequest(user.getName(), user.getPass(),
                    (InstructorUser) user, now);
        } else {
            userModel = new UserRegSaveRequest(user.getName(), user.getPass(), user, now);
        }

        userGateway.save(userModel);
    }
}

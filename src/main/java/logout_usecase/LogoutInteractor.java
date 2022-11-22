package logout_usecase;

import entities.InstructorUser;
import entities.StudentUser;
import entities.User;
import entities.UserFactory;
import user_register_usecase.*;

import java.io.IOException;
import java.time.LocalDateTime;


public class LogoutInteractor implements LogoutInputBoundary {

    /**
     * The use case for logging out.
     */
    final LogoutGateway userGateway;

    final LogoutPresenter userPresenter;

    final User user;

    /**
     * @param gateway the logout gateway (which interacts with the User database)
     * @param logoutPresenter the logout presenter
     * @param u the User that is logging out
     */
    public LogoutInteractor(LogoutGateway gateway, LogoutPresenter logoutPresenter, User u) {
        this.userGateway = gateway;
        this.userPresenter = logoutPresenter;
        this.user = u;
    }

    /**
     * Save a new UserRegSaveRequest which contains all of the information in the User that is trying to
     * log out into the User database.
     * @param request the request to logout
     * @return the logout response
     * @throws IOException
     */
    @Override
    public LogoutResponseModel create(LogoutRequestModel request) throws IOException {

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

        LogoutResponseModel accResponseModel = new LogoutResponseModel(user.getName(), now.toString());
        return userPresenter.prepareSuccessView(accResponseModel);
    }
}

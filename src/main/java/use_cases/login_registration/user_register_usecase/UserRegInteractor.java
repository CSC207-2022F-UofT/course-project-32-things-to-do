package use_cases.login_registration.user_register_usecase;

import entities.*;

import java.io.IOException;
import java.time.LocalDateTime;

// Use Case Layer

public class UserRegInteractor implements UserRegInputBoundary {

    /**
     * The use case for registering a user. Responsible for registering a valid new user.
     */

    final UserRegGateway userGateway;

    final UserRegPresenter userPresenter;

//    private UserFactory userFactory;

    private User user;

    /**
     * @param gateway the gateway that interacts with the User database
     * @param userRegPresenter the presenter that shows the success or failure of this attempt to register
     */
    public UserRegInteractor(UserRegGateway gateway, UserRegPresenter userRegPresenter) {
        this.userGateway = gateway;
        this.userPresenter = userRegPresenter;
//        this.userFactory = null;
    }

    /**
     * @param request the request to register this user
     * @return the response to whether this request to register was successful
     * @throws IOException
     */
    @Override
    public UserRegResponse create(UserRegRequest request) throws IOException {
        if (userGateway.existsByName(request.getName())) {
            return userPresenter.prepareFailView("That username is taken!");
        } else if (!request.getPassword().equals(request.getReenterPassword())) {
            return userPresenter.prepareFailView("Passwords entered do not match.");
        } else if (!request.getTypeOfUser().equals("Instructor") && !request.getTypeOfUser().equals("Student")) {
            return userPresenter.prepareFailView("Enter either 'Instructor' or 'Student'.");
        }

        UserFactory userFactory;
        if (request.getTypeOfUser().equals("Instructor")) {
            userFactory = new InstructorUserFactory();
        } else {
            userFactory = new StudentUserFactory();
        }

        User user = userFactory.create(request.getName(), request.getPassword());
        if (!user.checkPassword()) {
            return userPresenter.prepareFailView("Password must be at least 9 characters long");
        }

        this.user = user;

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

        UserRegResponse accResponseModel = new UserRegResponse(user.getName(), now.toString());
        return userPresenter.prepareSuccessView(accResponseModel);
    }

    public User getUser() {
        return this.user;
    }
}

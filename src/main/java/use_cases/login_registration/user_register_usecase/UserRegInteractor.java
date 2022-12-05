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

    final UserFactory userFactory;

    private User user;

    /**
     * @param gateway the gateway that interacts with the User database
     * @param userRegPresenter the presenter that shows the success or failure of this attempt to register
     * @param factory the factory that creates Users
     */
    public UserRegInteractor(UserRegGateway gateway, UserRegPresenter userRegPresenter, UserFactory factory) {
        this.userGateway = gateway;
        this.userPresenter = userRegPresenter;
        this.userFactory = factory;
    }

    /**
     * @param request the request to register this user
     * @return the response to whether this request to register was successful
     * @throws IOException if anything goes wrong
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

        // can make userFactory have a createUser() method that takes in the Name, Pass, and typeOfUser
        // and creates that type of user (with an if statement)

        if (request.getTypeOfUser().equals("Instructor")) {
            this.user = userFactory.createInstructor(request.getName(), request.getPassword());
        } else // initialize a StudentUser
             {
            this.user = userFactory.createStudent(request.getName(), request.getPassword());
        }

        if (!user.checkPassword()) {
            return userPresenter.prepareFailView("Password must be at least 9 characters long");
        }

        LocalDateTime now = LocalDateTime.now();

        //set the program's currently logged in user
        CurrentUser.setCurrentUser(user);

        UserRegSaveRequest userModel = getUserRegSaveRequest(now);

        userGateway.save(userModel);

        UserRegResponse accResponseModel = new UserRegResponse(user.getName(), now.toString());
        return userPresenter.prepareSuccessView(accResponseModel);
    }

    private UserRegSaveRequest getUserRegSaveRequest(LocalDateTime now) {
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
        return userModel;
    }

    /**
     * For testing purposes only!! Do not actually use.
     * @param now time of creation
     */
    public UserRegSaveRequest getUserSaveRequest(LocalDateTime now) {
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
        return userModel;
    }

    public User getUser() {
        if ((this.user instanceof StudentUser) | (this.user instanceof InstructorUser)) {
            return this.user;
        } else {
            return null;
        }
    }
}

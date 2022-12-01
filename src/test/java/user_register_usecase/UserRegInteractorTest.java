package user_register_usecase;

import entities.GeneralUserFactory;
import entities.StudentUser;
import entities.UserFactory;
import org.junit.jupiter.api.Test;
import screens.login_registration.InMemoryUser;
import screens.login_registration.UserRegResponseFormatter;
import use_cases.login_registration.user_register_usecase.*;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class UserRegInteractorTest {

    @Test
    void create() throws IOException {
        // To test the use case:
        // 1) Create a UserRegisterInteractor and prerequisite objects
        //    (arguments for the UserRegisterInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case User Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        // 1) UserRegisterInteractor and prerequisite objects
        // We're going to need a place to save and look up information. We could
        // use FileUser, but because unit tests are supposed to be independent
        // that would make us also reset the file when we are done.
        // Instead, we're going to "mock" that info using a short-lived solution
        // that just keeps the info in a dictionary, and it won't be persistent.
        // (Separately, elsewhere, we will need to test the FileUser works
        // properly.)
        UserRegGateway userRepository = new InMemoryUser();

        // This creates an anonymous implementing class for the Output Boundary.
        UserRegPresenter presenter = new UserRegResponseFormatter() {

            @Override
            public UserRegResponse prepareSuccessView(UserRegResponse user) {
                // 4) Check that the Output Data and associated changes
                // are correct
                assertEquals("paul", user.getLogin());
                assertNotNull(user.getCreationTime()); // any creation time is fine.
                assertTrue(userRepository.existsByName("paul"));
                return null;
            }

            @Override
            public UserRegResponse prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        UserFactory userFactory = new GeneralUserFactory();
        UserRegInputBoundary interactor = new UserRegInteractor(
                userRepository, presenter, userFactory);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        UserRegRequest inputData = new UserRegRequest(
                "paul", "pwd1234567", "pwd1234567", "Student");

        // 3) Run the use case
        interactor.create(inputData);
    }

    LocalDateTime now = LocalDateTime.now();
    StudentUser s = new StudentUser("Saje", "diffusers");
    UserRegGateway userRepository = new InMemoryUser();
    UserRegPresenter presenter = new UserRegResponseFormatter();
    UserFactory userFactory = new GeneralUserFactory();
    UserRegInputBoundary interactor2 = new UserRegInteractor(userRepository, presenter, userFactory);
    UserRegRequest inputData = new UserRegRequest(
            "Saje", "diffusers", "diffusers", "Student");

//    interactor.create(inputData);


    @Test
    void testGetUserRegSaveRequest() throws IOException {

        StudentSaveRequest ussr = new StudentSaveRequest("Saje", "diffusers", s, now);
        ((UserRegInteractor) interactor2).create(inputData);
        UserRegSaveRequest ussr2 = ((UserRegInteractor) interactor2).getUserSaveRequest(now);
        assertTrue(ussr.getName().equals(ussr2.getName()));
        assertTrue(ussr.getPass().equals(ussr2.getPass()));
        assertTrue(ussr instanceof StudentSaveRequest);

    }
}

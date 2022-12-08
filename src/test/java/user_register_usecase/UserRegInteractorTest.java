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

        UserRegGateway userRepository = new InMemoryUser();

        // Create an anonymous implementing class for the Output Boundary.
        UserRegPresenter presenter = new UserRegResponseFormatter() {

            @Override
            public UserRegResponse prepareSuccessView(UserRegResponse user) {
                //
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

        // Make up input data for the test
        UserRegRequest inputData = new UserRegRequest(
                "paul", "pwd1234567", "pwd1234567", "Student");

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


    @Test
    void testGetUserRegSaveRequest() throws IOException {

        // Test to see if getting the UserRegSaveRequest works (specifically for a student--Instructor is
        // the same thing)
        // Test if the name, password are stored correctly and

        StudentSaveRequest ussr = new StudentSaveRequest("Saje", "diffusers", s, now);
        ((UserRegInteractor) interactor2).create(inputData);
        UserRegSaveRequest ussr2 = ((UserRegInteractor) interactor2).getUserSaveRequest(now);
        assertTrue(ussr.getName().equals(ussr2.getName()));
        assertTrue(ussr.getPass().equals(ussr2.getPass()));
        assertTrue(ussr instanceof StudentSaveRequest);

    }
}

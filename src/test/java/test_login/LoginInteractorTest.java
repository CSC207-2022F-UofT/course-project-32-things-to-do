package test_login;

import entities.*;
import org.junit.jupiter.api.Test;
import screens.login_registration.*;
import use_cases.login_registration.login_usecase.*;
import use_cases.login_registration.user_register_usecase.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LoginInteractorTest {

    @Test
    void create() throws LoginFailed {

        InstructorUser u = new InstructorUser("paul", "pwd1234567");
        InstructorSaveRequest i = new InstructorSaveRequest("paul", "pwd1234567",
                u, LocalDateTime.now());
        InMemoryUser userRepository = new InMemoryUser();
        userRepository.save(i);

        // This creates an anonymous implementing class for the Output Boundary.
        LoginPresenter presenter = new LoginResponseFormatter() {

            @Override
            public LoginResponseModel prepareSuccessView(LoginResponseModel login) {
                // 4) Check that the Output Data and associated changes
                // are correct
                assertEquals("paul", login.getName());
                assertNotNull(login.getLoginTime()); // any creation time is fine.
                assertTrue(userRepository.existsByName("paul"));
                return null;
            }

            @Override
            public LoginResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        LoginInteractor interactor = new LoginInteractor(userRepository, presenter);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        LoginRequestModel inputData = new LoginRequestModel("paul", "pwd1234567");

        // 3) Run the use case
        interactor.create(inputData);

    }

}

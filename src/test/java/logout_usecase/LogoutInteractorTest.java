package logout_usecase;

import entities.GeneralUserFactory;
import entities.InstructorUser;
import entities.User;
import entities.UserFactory;
import org.junit.jupiter.api.Test;
import screens.login_registration.InMemoryUser;
import screens.login_registration.LogoutResponseFormatter;
import screens.login_registration.UserRegResponseFormatter;
import use_cases.login_registration.logout_usecase.*;
import use_cases.login_registration.user_register_usecase.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class LogoutInteractorTest {

    @Test
    void create() throws IOException {

        LogoutGateway userRepository = new InMemoryUser();

        // This creates an anonymous implementing class for the Output Boundary.
        LogoutPresenter presenter = new LogoutResponseFormatter() {

            @Override
            public LogoutResponseModel prepareSuccessView(LogoutResponseModel logout) {
                // 4) Check that the Output Data and associated changes
                // are correct
                assertEquals("paul", logout.getName());
                assertNotNull(logout.getLogoutTime()); // any creation time is fine.
                return null;
            }

            public LogoutResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }

        };

        User u = new InstructorUser("paul", "123456789");
        LogoutInputBoundary interactor = new LogoutInteractor(userRepository, presenter, u);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        LogoutRequestModel request = new LogoutRequestModel();

        // 3) Run the use case
        interactor.create(request);

        // was the user saved after logout?
        assert ((InMemoryUser) userRepository).existsByName("paul");
    }
}

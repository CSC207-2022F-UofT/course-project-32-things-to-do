package logout_usecase;

import entities.*;
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

        User u = new InstructorUser("paul", "123456789");
        CurrentUser.setCurrentUser(u);
        LogoutInputBoundary interactor = new LogoutInteractor(userRepository);

        // 3) Run the use case
        interactor.create();

        // was the user saved after logout?
        assert ((InMemoryUser) userRepository).existsByName("paul");
    }
}

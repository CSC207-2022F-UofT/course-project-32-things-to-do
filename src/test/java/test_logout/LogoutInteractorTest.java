package test_logout;

import entities.*;
import org.junit.jupiter.api.Test;
import screens.login_registration.*;
import use_cases.login_registration.logout_usecase.*;

import java.io.IOException;

public class LogoutInteractorTest {

    @Test
    void create() throws IOException {

        InMemoryUser userRepository = new InMemoryUser();

        User u = new InstructorUser("jallope", "123456789");
        CurrentUser.setCurrentUser(u);

        LogoutInputBoundary interactor = new LogoutInteractor(userRepository);

        // 3) Run the use case
        interactor.create();

        // was the user saved after logout?
        assert userRepository.existsByName("jallope");
    }
}

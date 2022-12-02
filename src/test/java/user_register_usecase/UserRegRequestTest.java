package user_register_usecase;

import org.junit.jupiter.api.Test;
import use_cases.login_registration.user_register_usecase.UserRegRequest;

public class UserRegRequestTest {

    @Test
    void testSetTypeOfUserDoesntWork() {
        // doesn't matter if password is valid or not, because UserRegRequest does not worry about that
        UserRegRequest r = new UserRegRequest("Poha", "small", "small",
                "Student");
        String s = r.setTypeOfUser("Stu");
        assert s.equals("User type can only be Instructor or Student");
    }

    @Test
    void testSetTypeOfUserWorks() {
        UserRegRequest r = new UserRegRequest("Poha", "small", "small",
                "Student");
        String s = r.setTypeOfUser("Instructor");
        assert s.equals("Success");
    }
}

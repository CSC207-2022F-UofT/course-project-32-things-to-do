package user_register_usecase;

import entities.InstructorUser;
import entities.StudentUser;
import entities.User;
import org.junit.jupiter.api.Test;
import use_cases.login_registration.user_register_usecase.InstructorSaveRequest;
import use_cases.login_registration.user_register_usecase.StudentSaveRequest;
import use_cases.login_registration.user_register_usecase.UserRegSaveRequest;

import java.time.LocalDateTime;

public class UserRegSaveRequestTest {

    @Test
    void testinitializeStudentUser() {
        StudentUser u = new StudentUser("poodles", "flooperflopple");
        StudentSaveRequest ussr = new StudentSaveRequest("poodles", "flooperflopple", u,
                LocalDateTime.now());
        User u2 = ussr.initializeUser();
        assert u2 != null;
        assert u2.getName().equals("poodles");
        assert u2.getPass().equals("flooperflopple");
    }

    @Test
    void testinitializeInstructorUser() {
        InstructorUser u = new InstructorUser("poodles", "flooperflopple");
        InstructorSaveRequest ussr = new InstructorSaveRequest("poodles", "flooperflopple", u,
                LocalDateTime.now());
        User u2 = ussr.initializeUser();
        assert u2 != null;
        assert u2.getName().equals("poodles");
        assert u2.getPass().equals("flooperflopple");
    }

}

import entities.InstructorUser;
import entities.StudentUser;
import org.junit.jupiter.api.Test;
import screens.login_registration.FileUser;
import use_cases.login_registration.user_register_usecase.InstructorSaveRequest;
import use_cases.login_registration.user_register_usecase.StudentSaveRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


public class FileUserTest {

    // Can it save a StudentUser and an InstructorUser?
    // Can it read the file? Is the data preserved?
    // Does it save updates to users?

    StudentUser student = new StudentUser("dhakaad", "badassb4real");
    InstructorUser instructor = new InstructorUser("zinda", "bollywood");
    StudentSaveRequest ssri = new StudentSaveRequest("dhakaad", "badassb4real", student,
            LocalDateTime.now());
    InstructorSaveRequest bruh = new InstructorSaveRequest("zinda", "bollywood", instructor,
            LocalDateTime.now());

    public FileUserTest() throws IOException, ClassNotFoundException {
    }

    @Test
    void saveUsers() throws IOException, ClassNotFoundException {
        FileUser f_u = new FileUser("src/test/java/data/userstest.ser");
        f_u.save(ssri);
        assert f_u.existsByName("dhakaad");
        f_u.save(bruh);
        assert f_u.existsByName("zinda");
    }

    StudentSaveRequest ssri2 = new StudentSaveRequest("plant", "vanillawhey", student,
            LocalDateTime.now());
    InstructorSaveRequest bruh2 = new InstructorSaveRequest("cacti", "waterbottle",
            instructor, LocalDateTime.now());

    @Test
    void readUsers() throws IOException, ClassNotFoundException {
        FileUser f_u2 = new FileUser("src/test/java/data/userstest.ser");
        f_u2.save(ssri2);
        f_u2.save(bruh2);
        Set<String> s = f_u2.getAccounts().keySet();
        String[] names = {"zinda", "dhakaad", "plant", "cacti"};
        assert s.containsAll(List.of(names));
    }

    StudentSaveRequest ssri3 = new StudentSaveRequest("plant", "changedPass", student,
            LocalDateTime.now());
    @Test
    void updateUsers() throws IOException, ClassNotFoundException {
        FileUser f_u3 = new FileUser("src/test/java/data/userstest.ser");
        f_u3.save(ssri3);
        Map accounts = f_u3.getAccounts();
        StudentSaveRequest s = (StudentSaveRequest) accounts.get("plant");
        assert s.getPass().equals("changedPass");

    }


}

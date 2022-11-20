import entities.StudentUser;
import org.junit.jupiter.api.Test;
import screens.FileUser;
import user_register_usecase.StudentSaveRequest;

import java.io.IOException;
import java.time.LocalDateTime;

public class FileUserTest {

    @Test
    void saveStudentUser() throws IOException, ClassNotFoundException {
        StudentUser student = new StudentUser("gma", "veryverysad");
        StudentSaveRequest ssri = new StudentSaveRequest("gma", "veryverysad", student,
                LocalDateTime.now());
        FileUser f_u = new FileUser("./userstest.ser");
        f_u.save(ssri);
        System.out.println(f_u.getAccounts());
    }


}

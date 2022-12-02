package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserUnitTest {

    @Test
    void testShortPassStudent() {
        User user = new StudentUser("Rex", "7567");

        assertFalse(user.checkPassword());
    }

    @Test
    void testShortPassInstructor() {
        User user = new InstructorUser("Cody", "2224");

        assertFalse(user.checkPassword());
    }

    @Test
    void testGoodPassStudent() {
        User user = new StudentUser("Ahsoka", "123456789");

        assertTrue(user.checkPassword());
    }

    @Test
    void testGoodPassInstructor() {
        User user = new InstructorUser("Anakin", "123456789");

        assertTrue(user.checkPassword());
    }

    @Test
    void testNoPassInstructor() {
        User user = new InstructorUser("Jesse", "");

        assertFalse(user.checkPassword());
    }

    @Test
    void testNoPassStudent() {
        User user = new StudentUser("Cody", "");

        assertFalse(user.checkPassword());
    }
}

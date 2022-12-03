package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserFactoryTest {

    @Test
    void testCreateStudentGoodPass() {
        GeneralUserFactory g = new GeneralUserFactory();
        StudentUser s = g.createStudent("Fives", "123456789");
        // doing this because if it wasn't a StudentUser, it wouldn't compile
        Assertions.assertTrue(s.checkPassword());
    }

    @Test
    void testCreateInstructorGoodPass() {
        GeneralUserFactory g = new GeneralUserFactory();
        InstructorUser s = g.createInstructor("Hardcase", "123456789");
        Assertions.assertTrue(s.checkPassword());
    }

    @Test
    void testCreateStudentBadPass() {
        GeneralUserFactory g = new GeneralUserFactory();
        StudentUser s = g.createStudent("Vaughn", "2468");
        Assertions.assertFalse(s.checkPassword());
    }

    @Test
    void testCreateInstructorBadPass() {
        GeneralUserFactory g = new GeneralUserFactory();
        InstructorUser s = g.createInstructor("Hardcase", "2468");
        Assertions.assertFalse(s.checkPassword());
    }
}

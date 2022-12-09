package use_cases.login_registration.user_register_usecase;

import entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class InstructorSaveRequest extends UserRegSaveRequest {
    /**
     * A transient data storage class that contains the same information as an InstructorUser
     */

    private final String name;

    private final String password;

    private final ArrayList<String> courses;

    /**
     * @param name the name of this Instructor
     * @param password the password of this Instructor
     * @param instructor the InstructorUser that needs to be saved into this data storage object
     * @param creationTime the time at which this InstructorUser was created or last saved
     */
    public InstructorSaveRequest(String name, String password, InstructorUser instructor, LocalDateTime creationTime) {
        super(name, password);
        this.name = name;
        this.password = password;
        this.courses = instructor.getCourses();
    }

    public ArrayList<String> getCourses() {
        return this.courses;
    }

    @Override
    public InstructorUser initializeUser() {
        InstructorUser i = new InstructorUser(this.name, this.password);
        i.setCourses(this.courses);
        return i;
    }

}

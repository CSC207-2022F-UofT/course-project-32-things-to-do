package user_register_usecase;

import entities.InstructorUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class InstructorSaveRequest extends UserRegSaveRequest {

    private String name;

    private String password;

    private LocalDateTime creationTime;

    private final ArrayList<String> courses;

    public InstructorSaveRequest(String name, String password, InstructorUser instructor, LocalDateTime creationTime) {
        super(name, password, instructor, creationTime);
        this.courses = instructor.getCourses();
    }

    public ArrayList<String> getCourses() {
        return this.courses;
    }

}

package user_register_usecase;

import entities.InstructorUser;
import entities.StudentUser;
import entities.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Use Case Layer

public class UserRegSaveRequest implements Serializable {

    private final String name;

    private String password;

    private final LocalDateTime creationTime;

    public UserRegSaveRequest(String name, String password, User user, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
    }

    public String getName() { return name; }

    public String getPass() { return password; }

    public void setPass(String password) {
        this.password = password;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

}

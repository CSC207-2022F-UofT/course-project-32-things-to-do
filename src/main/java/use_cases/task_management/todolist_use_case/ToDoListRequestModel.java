package use_cases.task_management.todolist_use_case;

import entities.StudentUser;
import use_cases.login_registration.user_register_usecase.StudentSaveRequest;

/**
 * Request Model for the ToDoList Use Case
 * Located in the use case layer, acts as the output data object
 */

public class ToDoListRequestModel {

    //TODO StudentSaveRequest
    //private final StudentSaveRequest studentSaveRequest;

    private final StudentUser studentUser;

//    public ToDoListRequestModel(StudentSaveRequest studentSaveRequest) {
//        this.studentSaveRequest = studentSaveRequest;
//    }

    public ToDoListRequestModel(StudentUser studentUser) {
        this.studentUser = studentUser;
    }

//    public StudentSaveRequest getStudentSaveRequest() {
//        return studentSaveRequest;
//    }

    public StudentUser getStudentUser() {
        return studentUser;
    }
}

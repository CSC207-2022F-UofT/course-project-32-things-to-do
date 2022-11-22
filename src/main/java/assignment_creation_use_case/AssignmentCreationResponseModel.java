package assignment_creation_use_case;

import java.time.LocalDateTime;

public class AssignmentCreationResponseModel {
    String title;
    LocalDateTime dueDate;

    public AssignmentCreationResponseModel(String title, LocalDateTime dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }
}

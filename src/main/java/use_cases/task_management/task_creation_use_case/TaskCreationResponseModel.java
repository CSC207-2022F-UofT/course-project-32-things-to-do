package use_cases.task_management.task_creation_use_case;

public class TaskCreationResponseModel {
    // the title of the Task
    private final String title;
    // the type of Task created (one of "Event", "Assignment", "Test")
    private final String type;

    public TaskCreationResponseModel(String title, String type) {
        this.title = title;
        this.type = type;
    }

    // getters
    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
}

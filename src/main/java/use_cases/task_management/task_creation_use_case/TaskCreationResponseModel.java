package use_cases.task_management.task_creation_use_case;

public class TaskCreationResponseModel {
    // the title of the Task
    private final String title;
    // the Task ID
    private final String id;
    // the type of Task created (one of "Event", "Assignment", "Test")
    private final String type;

    /**
     * A response model for successful Task creation
     * @param title - the title of the Task
     * @param type - the type of Task
     */
    public TaskCreationResponseModel(String title, String id, String type) {
        this.title = title;
        this.id = id;
        this.type = type;
    }

    // getters
    public String getTitle() {
        return title;
    }
    public String getId() {
        return this.id;
    }

    public String getType() {
        return type;
    }
}

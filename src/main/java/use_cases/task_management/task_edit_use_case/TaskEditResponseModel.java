package use_cases.task_management.task_edit_use_case;

public class TaskEditResponseModel {
    private final String title;
    private final String id;
    private final String type;

    /**
     * A response model for Task editing
     * @param title - the title of the Task
     * @param id - the ID of the Task
     * @param type - the type of Task
     */
    public TaskEditResponseModel(String title, String id, String type) {
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

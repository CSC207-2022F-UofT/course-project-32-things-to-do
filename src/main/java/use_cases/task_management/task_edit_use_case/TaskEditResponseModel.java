package use_cases.task_management.task_edit_use_case;

public class TaskEditResponseModel {
    private final String title;
    private final String type;
    public TaskEditResponseModel(String title, String type) {
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

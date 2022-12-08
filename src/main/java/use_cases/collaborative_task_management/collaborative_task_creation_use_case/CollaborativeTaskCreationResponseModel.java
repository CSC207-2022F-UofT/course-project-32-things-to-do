package use_cases.collaborative_task_management.collaborative_task_creation_use_case;

/**
 * Response Model for the Collaborative Task Creation Use Case
 * Acts as the output data object in the use case layer
 */

public class CollaborativeTaskCreationResponseModel {
    private final String title;
    private final String id;
    final String type = "Collaborative";
    /**
     * A response model for successful Collaborative task creation.
     * @param title - the title of the Collaborative Task
     * @param id - the Collaborative Task's id
     */
    public CollaborativeTaskCreationResponseModel(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
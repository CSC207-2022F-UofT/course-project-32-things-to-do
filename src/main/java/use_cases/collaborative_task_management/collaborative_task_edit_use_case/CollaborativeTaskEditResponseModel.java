package use_cases.collaborative_task_management.collaborative_task_edit_use_case;

/**
 * Response Model for the Collaborative Task Edit Use Case
 * Acts as the output data object in the use case layer
 */

public class CollaborativeTaskEditResponseModel {
    private final String title;
    private final String id;

    /**
     * A response model for Collaborative Task editing.
     * @param title - the title of the Collaborative Task.
     * @param id - the ID of the Collaborative Task.
     */
    public CollaborativeTaskEditResponseModel(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() { return title; }
    public String getId() { return id; }
}
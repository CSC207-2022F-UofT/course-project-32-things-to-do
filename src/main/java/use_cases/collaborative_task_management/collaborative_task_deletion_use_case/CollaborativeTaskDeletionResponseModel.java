package use_cases.collaborative_task_management.collaborative_task_deletion_use_case;

/**
 * Response Model for the Collaborative Task Deletion Use Case
 * Acts as the output data object in the use case layer
 */

public class CollaborativeTaskDeletionResponseModel {
    private final String title;
    private final String id;

    /**
     * A response model for Collaborative Task deletion.
     * @param title - the title of the Collaborative Task
     * @param id - the ID of the Collaborative Task
     */
    public CollaborativeTaskDeletionResponseModel(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {return this.title; }
    public String getId() { return this.id; }
}

package use_cases.collaborative_task_management.collaborative_task_creation_use_case;

import entities.StudentUser;
import java.time.LocalDateTime;

/**
 * Response Model for the Collaborative Task Creation Use Case
 * Acts as the output data object in the use case layer
 */

public class CollaborativeTaskCreationResponseModel {
    private final String title;
    private final String id;
    private final LocalDateTime deadline;
    private final StudentUser leader;

    /**
     * A response model for successful Collaborative task creation.
     * @param title - the title of the Collaborative Task
     * @param id - the Collaborative Task's id
     * @param deadline - the Collaborative Task's deadline
     * @param leader - the Collaborative Task's leader
     */
    public CollaborativeTaskCreationResponseModel(String title, String id,  LocalDateTime deadline, StudentUser leader) {
        this.title = title;
        this.id = id;
        this.deadline = deadline;
        this.leader = leader;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public StudentUser getLeader() {
        return leader;
    }
}
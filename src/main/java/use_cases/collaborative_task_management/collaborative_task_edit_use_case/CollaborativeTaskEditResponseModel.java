package use_cases.collaborative_task_management.collaborative_task_edit_use_case;

import entities.StudentUser;
import java.util.ArrayList;

public class CollaborativeTaskEditResponseModel {
    private final String title;
    private final String id;
    private final ArrayList<StudentUser> teammates;

    /**
     * A response model for Collaborative Task editing.
     * @param title - the title of the Collaborative Task.
     * @param id - the ID of the Collaborative Task.
     * @param teammates - the Collaborative Task's teammates.
     */
    public CollaborativeTaskEditResponseModel(String title, String id, ArrayList<StudentUser> teammates) {
        this.title = title;
        this.id = id;
        this.teammates = teammates;
    }

    public String getTitle() { return title; }
    public String getId() { return id; }
    public StudentUser getTeammates() { return teammates; }
}
package screens.task_management.task_edit_delete_screens.assignment_edit_delete_screens;

import entities.Assignment;
import entities.TaskMap;
import use_cases.task_management.task_edit_use_case.AssignmentDisplayer;

import java.time.LocalDateTime;

public class AssignmentInfoRetriever implements AssignmentDisplayer {
    private final Assignment assignment;
    private final String id;

    /**
     * An object that retrieves the info of an Assignment to be displayed in edit screens
     * @param id - ID for the Assignment whose info is being displayed
     */
    public AssignmentInfoRetriever(String id) {
        this.assignment = (Assignment) TaskMap.findTask(id);
        this.id = id;
    }
    @Override
    public String getTitle() {
        return assignment.getTitle();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getPriority() {
        return assignment.getPriority();
    }

    @Override
    public LocalDateTime getDueDate() {
        return assignment.getDueDate();
    }

    @Override
    public double getWeightage() {
        return assignment.getWeightage();
    }

    @Override
    public double getTimeNeeded() {
        return assignment.getTimeNeeded();
    }

    @Override
    public double getTimeSpent() {
        return assignment.getTimeSpent();
    }
}

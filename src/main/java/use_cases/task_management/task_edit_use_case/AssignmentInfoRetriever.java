package use_cases.task_management.task_edit_use_case;

import entities.Assignment;

import java.time.LocalDateTime;

public class AssignmentInfoRetriever implements AssignmentDisplayer {
    private final Assignment assignment;

    /**
     * An object that retrieves the info of an Assignment to be displayed in edit screens
     * @param assignment - the Assignment whose info is being displayed
     */
    public AssignmentInfoRetriever(Assignment assignment) {
        this.assignment = assignment;
    }
    @Override
    public String getTitle() {
        return assignment.getTitle();
    }

    @Override
    public String getId() {
        return assignment.getId();
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

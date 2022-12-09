package screens.collaborative_task_management.collaborative_task_edit_delete_screens;

import entities.CollaborativeTask;
import entities.StudentUser;
import entities.TaskMap;
import use_cases.collaborative_task_management.collaborative_task_edit_use_case.CollaborativeTaskDisplayer;

import java.time.LocalDateTime;

public class CollaborativeTaskInfoRetreiver implements CollaborativeTaskDisplayer{
    private final CollaborativeTask collaborativeTask;
    private final String id;

    /**
     * An object that retrieves the info of a Collaborative Task to be displayed in edit screens
     * @param id - ID of the Collaborative Task whose info is being displayed
     */
    public CollaborativeTaskInfoRetreiver(String id) {
        this.collaborativeTask = (CollaborativeTask) TaskMap.findTask(id);
        this.id = id;
    }

    @Override
    public String getTitle() { return collaborativeTask.getTitle(); };

    @Override
    public String getId() { return this.id; };

    @Override
    public int getPriority() { return collaborativeTask.getPriority(); };

    @Override
    public boolean isRecurring() { return collaborativeTask.getRecurring(); };

    @Override
    public String getFrequency() { return collaborativeTask.getFrequency(); };

    @Override
    public LocalDateTime getStartTime() { return collaborativeTask.getStartTime(); };

    @Override
    public LocalDateTime getEndTime() { return collaborativeTask.getEndTime(); };

    @Override
    public LocalDateTime getDeadline() { return collaborativeTask.getDeadline(); };

    @Override
    public StudentUser getLeader() { return collaborativeTask.getLeader(); };
}

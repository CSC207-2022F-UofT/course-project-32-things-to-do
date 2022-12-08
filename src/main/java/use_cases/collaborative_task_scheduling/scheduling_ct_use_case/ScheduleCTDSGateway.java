package use_cases.collaborative_task_scheduling.scheduling_ct_use_case;

import entities.*;

/**
 * Gateway includes methods overridden in in FileTaskMap
 */

public interface ScheduleCTDSGateway {

    void updateTaskMap(String taskID, CollaborativeTask updatedTask);
}

package use_cases.task_management.todolist_use_case;

import java.time.LocalDateTime;

public class ToDoListItem {

    private final String taskTitle;

    private final String taskId;

    private final TaskType taskType;

    private final LocalDateTime startTime;

    private final LocalDateTime endTime;

    private final boolean recurring;

    private final String frequency;

    public ToDoListItem(String taskTitle, String taskId, TaskType taskType, LocalDateTime startTime, LocalDateTime endTime,
                        boolean recurring, String frequency) {
        this.taskTitle = taskTitle;
        this.taskId = taskId;
        this.taskType = taskType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurring = recurring;
        this.frequency = frequency;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskId() {
        return taskId;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String isRecurring() {
        if (recurring) {
            return "true";
        } else {
            return "false";
        }
    }

    public String getFrequency() {
        return frequency;
    }
}

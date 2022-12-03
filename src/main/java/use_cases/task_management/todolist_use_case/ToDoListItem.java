package use_cases.task_management.todolist_use_case;

public class ToDoListItem {

    private final String taskTitle;

    private final String taskId;

    private final TaskType taskType;

    public ToDoListItem(String taskTitle, String taskId, TaskType taskType) {
        this.taskTitle = taskTitle;
        this.taskId = taskId;
        this.taskType = taskType;
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
}

package use_cases.task_management.todolist_use_case;

import entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Interactor for the ToDoList Use Case
 * Implements the business logic of this use case, located in the use case layer
 */

public class ToDoListInteractor implements ToDoListInputBoundary{

    private final ToDoListOutputBoundary outputBoundary;

    public ToDoListInteractor(ToDoListOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * For each of the tasks represented in the toDoList instance variable of the logged-in StudentUser, get a list
     * of ToDoListItem items for these tasks (containing the task name, task id and task type) to display on the
     * ToDoListScreen.
     * @return the list of ToDoListItem items representing the tasks in the student's todo list
     */
    @Override
    public ToDoListResponseModel getToDoList() {
        try {
            StudentUser studentUser;
            if (CurrentUser.isStudent()) {
                studentUser = (StudentUser) CurrentUser.getCurrentUser();
            } else {
                throw new RuntimeException("You are not a student!");
            }

            ArrayList<String> taskIdList = studentUser.getToDoList();
            ArrayList<ToDoListItem> toDoList = new ArrayList<>();

            for (String taskId : taskIdList) {
                //get the Task object for this task from the entity TaskMap static variable
                Task task = TaskMap.findTask(taskId);
                String taskItemTitle = task.getTitle();
                String taskItemId = task.getId();
                LocalDateTime startTime = null;
                LocalDateTime endTime = null;
                boolean recurring = false;
                String frequency = null;

                TaskType taskItemType;
                if (task instanceof Event) {
                    taskItemType = TaskType.EVENT;
                    LocalDateTime[] timeBlock = ((Event) task).getTimeBlock();
                    startTime = timeBlock[0];
                    endTime = timeBlock[1];
                    recurring = ((Event) task).getRecurring();
                    frequency = ((Event) task).getFrequency();
                } else if (task instanceof Test) {
                    taskItemType = TaskType.TEST;
                    LocalDateTime[] timeBlock = ((Test) task).getTimeBlock();
                    startTime = timeBlock[0];
                    endTime = timeBlock[1];
                } else if (task instanceof Assignment) {
                    taskItemType = TaskType.ASSIGNMENT;
                } else if (task instanceof CollaborativeTask) {
                    startTime = ((CollaborativeTask) task).getStartTime();
                    endTime = ((CollaborativeTask) task).getEndTime();
                    recurring = ((CollaborativeTask) task).getRecurring();
                    frequency = ((CollaborativeTask) task).getFrequency();
                    taskItemType = TaskType.COLLABORATIVE;
                } else {
                    throw new RuntimeException("Some of your tasks are an invalid Task Type!");
                }

                ToDoListItem toDoListItem = new ToDoListItem(taskItemTitle, taskItemId, taskItemType, startTime, endTime, recurring, frequency);
                toDoList.add(toDoListItem);
            }

            return outputBoundary.display(new ToDoListResponseModel(toDoList));
        } catch (Exception e) {
            return outputBoundary.failView(e.getMessage());
        }

    }

}

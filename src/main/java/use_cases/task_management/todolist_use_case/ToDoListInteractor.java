package use_cases.task_management.todolist_use_case;

import entities.*;

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
     *
     * @param requestModel - contains a representation of the logged-in StudentUser
     * @return the list of ToDoListItem items representing the tasks in the student's todo list
     */
    @Override
    public ToDoListResponseModel getToDoList(ToDoListRequestModel requestModel) {

        //TODO need Natalie's StudentSaveRequest.initializeUser method!!
        // StudentUser studentUser = requestModel.getStudentSaveRequest.initializeUser();

        StudentUser studentUser = requestModel.getStudentUser();

        ArrayList<String> taskIdList = studentUser.getToDoList();
        ArrayList<ToDoListItem> toDoList = new ArrayList<>();

        for (String taskId: taskIdList) {
            //get the Task object for this task from the entity TaskMap static variable
            Task task = TaskMap.getTaskMap().get(taskId);
            String taskItemTitle = task.getTitle();
            String taskItemId = task.getId();

            TaskType taskItemType;
            if (task instanceof Event) {
                taskItemType = TaskType.EVENT;
            } else if (task instanceof Test) {
                taskItemType = TaskType.TEST;
            } else if (task instanceof Assignment) {
                taskItemType = TaskType.ASSIGNMENT;
            } else {
                continue; // ignore any other types of tasks
            }

            ToDoListItem toDoListItem = new ToDoListItem(taskItemTitle, taskItemId, taskItemType);
            toDoList.add(toDoListItem);
        }

        return outputBoundary.display(new ToDoListResponseModel(toDoList));

    }

}

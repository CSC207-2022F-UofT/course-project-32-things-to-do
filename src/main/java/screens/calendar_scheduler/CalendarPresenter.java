package screens.calendar_scheduler;

import screens.task_management.todolist_screens.*;
import use_cases.task_management.todolist_use_case.*;

import java.util.ArrayList;

public class CalendarPresenter implements ToDoListOutputBoundary {

    private ToDoListInputBoundary toDoListInput;

    public void setToDoListInput(ToDoListInputBoundary toDoListInput) {
        this.toDoListInput = toDoListInput;
    }

    public ArrayList<ArrayList<String>> getToDoList() {
        ToDoListResponseModel responseModel = toDoListInput.getToDoList();
        return responseModel.getToDoListView();
    }

    @Override
    public ToDoListResponseModel display(ToDoListResponseModel responseModel) {

        // Initialise task list array
        ArrayList<ArrayList<String>> taskList = new ArrayList<>();

        // Iterate through each task in to-do list
        for (ToDoListItem item : responseModel.getToDoList()) {

            // Initialise task item array
            ArrayList<String> taskItem = new ArrayList<>();

            // Get title, task type, start time, end time, recurrence and frequency of task item
            if (item.getTaskType().equals(TaskType.TEST)) {
                taskItem.add(item.getTaskTitle());
                taskItem.add("Test");
                taskItem.add(item.getStartTime().toString());
                taskItem.add(item.getEndTime().toString());
                taskItem.add(null);
                taskItem.add(null);
            } else if (item.getTaskType().equals(TaskType.EVENT)) {
                taskItem.add(item.getTaskTitle());
                taskItem.add("Event");
                taskItem.add(item.getStartTime().toString());
                taskItem.add(item.getEndTime().toString());
                taskItem.add(item.isRecurring());
                taskItem.add(item.getFrequency());
            } else {
                taskItem.add(item.getTaskTitle());
                taskItem.add("Assignment");
                taskItem.add(null);
                taskItem.add(null);
                taskItem.add(null);
                taskItem.add(null);
            }

            // Add task item to task list array
            taskList.add(taskItem);
        }

        responseModel.setToDoListView(taskList);

        return responseModel;

    }

    @Override
    public ToDoListResponseModel failView(String error) {
        throw new ToDoListFail(error);
    }
}

package use_cases.task_management.todolist_use_case;

import java.util.ArrayList;

/**
 * RequestModel for the ToDoList Use Case
 * Located in the Interface Adapter (Use Case) Layer, acts as the input data object
 */

public class ToDoListResponseModel {

    private final ArrayList<ToDoListItem> toDoList;

    private ArrayList<ArrayList<String>> toDoListView;

    public ToDoListResponseModel(ArrayList<ToDoListItem> toDoList) {
        this.toDoList = toDoList;
    }

    public ArrayList<ToDoListItem> getToDoList() {
        return toDoList;
    }

    public ArrayList<ArrayList<String>> getToDoListView() {
        return toDoListView;
    }

    public void setToDoListView(ArrayList<ArrayList<String>> toDoListView) {
        this.toDoListView = toDoListView;
    }
}

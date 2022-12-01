package use_cases.task_management.todolist_use_case;

/**
 * Input Boundary interface for the ToDoList Use Case
 */

public interface ToDoListInputBoundary {
    ToDoListResponseModel getToDoList(ToDoListRequestModel requestModel);
}
